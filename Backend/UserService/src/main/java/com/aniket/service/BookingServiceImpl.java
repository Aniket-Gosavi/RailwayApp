package com.aniket.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.aniket.model.TransactionDetails;
import com.aniket.repository.BookingRepo;
import com.aniket.repository.TrainRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import mailservice.EmailServiceImpl;

@Service
public class BookingServiceImpl implements BookingService{
	
	private static final String KEY = "rzp_test_EA4qic89tasagG";
	  
	  private static final String KEY_SECRET = "2y5w46qbfQEPY8zMqVpZHenf";
	
	private static final String CURRENCY = "INR";
	
	private static final Logger log = LoggerFactory.getLogger(BookingServiceImpl.class);
	
	@Autowired
	private BookingRepo brepo;
	
	@Autowired
	private TrainRepo trepo;

	@Autowired
	private EmailServiceImpl esi;

	@Autowired
	SequenceGeneratorService seq;
	
	@Autowired
	RestTemplate rest;
	
	String email;
	
	LocalDate ld;
	
	@Override
	public List<TrainDetails> showTrains() {
		return trepo.findAll();
	}


	@Override
	public Booking showBookingById(int id) throws ResourceNotFoundException {
		Optional<Booking> book = brepo.findById(id);
		if(book.isEmpty()) {
			log.info("booking not found by the given ID"+id);
			throw new ResourceNotFoundException("not found");
		}
		Booking bk = book.get();
		return bk;
	}

	@Override
	public Booking showBookingByName(String name) throws ResourceNotFoundException {
		Booking book = brepo.findAllByFirstName(name);
		if(book == null) {
			log.warn("booking not found by the given name "+name);
			throw new ResourceNotFoundException("not found");
		}
		return book;
	}
	
	public List<Booking> showBookingByEmail(){
		email = rest.getForObject("http://localhost:9098/api/auth/email", String.class);
		List<Booking> book = brepo.findAllByEmail(email);
		return book;
	}


	@Override
	public Booking bookTrain(Booking book) {
		TrainDetails traindetails = trepo.findByTrainNo(book.getTrainNo());
		email = rest.getForObject("http://localhost:9098/api/auth/email", String.class);
		book.setEmail(email);
		book.setId(seq.getSequenceNum(Booking.sequenceName));
		double fair = traindetails.getFair();
		book.setAmount(fair * book.getNumberOfTravellers());
		book.setDate(ld.now());
		Booking bk = brepo.save(book);
		traindetails.setTicketsAvailable(traindetails.getTicketsAvailable() - book.getNumberOfTravellers());
		trepo.save(traindetails);
//		String body = "Hello "+book.getFirstName()+" "+book.getLastName()+" ,We have received your booking for ID:"+book.getId()+""
//				+ "\n Boarding Station: "+traindetails.getBoardingStation()+""
//				+ "\n Destination: "+traindetails.getDestination()+""
//				+ "\n Train Name: "+traindetails.getName()+""+
//				"\n Train Timing: "+traindetails.getTiming()+
//				"\n Train Date: "+traindetails.getDate()+
//				"\n Wishing you A Happy Journey Ahead";
//		esi.sendSimpleMail(book.getEmail(), body, "Booking Details");
		log.info("Booking successfully done for ID"+book.getId());
		return bk;
	}


	public List<TrainDetails> showBySourceAndDestination(String source, String destination,String date) throws ResourceNotFoundException{
		List<TrainDetails> show = trepo.findByBoardingStationAndDestinationAndDate(source, destination,date);
		if(show.isEmpty()) {
			throw new ResourceNotFoundException("not found");
		}
		return show;
	}
	

	@Override
	public Booking cancelTicket(int id) throws ResourceNotFoundException{
		Optional<Booking> deleteBooking = brepo.findById(id);
		if(deleteBooking.isEmpty()) {
			throw new ResourceNotFoundException("Booking not fount by the given Id" +id);
		}
		Booking cancel = deleteBooking.get();
		int trainno = cancel.getTrainNo();
		TrainDetails td = trepo.findByTrainNo(trainno);
		td.setTicketsAvailable(td.getTicketsAvailable() + cancel.getNumberOfTravellers());
		trepo.save(td);
		brepo.delete(cancel);
		String body = "Hello "+cancel.getFirstName()+" "+cancel.getLastName()+" ,We have Cancelled your booking for ID:"+cancel.getId()+"";
		esi.sendSimpleMail(cancel.getEmail(), body, "Booking Details");
		return cancel;
	}


	@Override
	public List<TrainDetails> showByDate(LocalDate date) {
		return trepo.findByDate(date);
	}
	
	public double findByNo(int tno) {
		TrainDetails td = trepo.findByTrainNo(tno);
		return td.getFair();
	}
	

	public TransactionDetails createTransaction(double amount) {
		try {
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("amount", (amount * 100));
			jsonObject.put("currency", CURRENCY);
			
			RazorpayClient razorpayClient = new RazorpayClient(KEY, KEY_SECRET);
			
			Order order = razorpayClient.orders.create(jsonObject);
			
			return prepareTransactionDetails(order);
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private TransactionDetails prepareTransactionDetails(Order order) {
		String orderId = order.get("id");
		String currency = order.get("currency");
		Integer amount = order.get("amount");
		
		TransactionDetails details = new TransactionDetails(orderId,currency,amount,KEY);
		return details;
	}

}
