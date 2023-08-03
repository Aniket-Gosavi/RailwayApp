package com.aniket.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.aniket.model.TransactionDetails;
import com.aniket.repository.TrainRepo;
import com.aniket.service.BookingService;
import com.aniket.service.BookingServiceImpl;
import com.razorpay.RazorpayException;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	private TrainRepo trepo;

	@Autowired
	private BookingServiceImpl bk;

	@PostMapping("/book")
	public ResponseEntity<?> bookTrain(@RequestBody Booking book) {
		return ResponseEntity.ok(bk.bookTrain(book));
	}

	@GetMapping("/show")
	public ResponseEntity<?> showTrain() throws ResourceNotFoundException {
		List<TrainDetails> trainDetails = trepo.findAll();
		if(trainDetails.isEmpty()) {
			throw new ResourceNotFoundException("No Data Available to show");
		}
		return ResponseEntity.ok(bk.showTrains());
	}

	@GetMapping("/findbyId/{id}")
	public ResponseEntity<Booking> findById(@PathVariable int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.showBookingById(id));
	}
	
	@GetMapping("/findbyname/{name}")
	public ResponseEntity<?> findByName(@PathVariable String name) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.showBookingByName(name));
	}
	@GetMapping("/findbyno/{tno}")
	public ResponseEntity<?> findByNo(@PathVariable int tno){
		return ResponseEntity.ok(bk.findByNo(tno));
	}
	
	@GetMapping("/findbysourceanddestination/{source}/{destination}/{date}")
	public ResponseEntity<?> findBySourceAndDestination(@PathVariable String source, @PathVariable String destination, @PathVariable String date) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.showBySourceAndDestination(source,destination,date));
	}
	
	@GetMapping("/findbyDate/{date}")
	public ResponseEntity<?> findByDate(@PathVariable LocalDate date) {
		return ResponseEntity.ok(bk.showByDate(date));
	}
	
	@DeleteMapping("/cancelbooking/{id}")
	public ResponseEntity<?> cancelBooking(@PathVariable int id) throws ResourceNotFoundException {
		return ResponseEntity.ok(bk.cancelTicket(id));
	}
	
	@GetMapping("/createTransaction/{amount}")
	public TransactionDetails createTransaction(@PathVariable double amount) {
		return bk.createTransaction(amount);
	}
	
	@GetMapping("/findbyemail")
	public ResponseEntity<?> showBookingByEmail() {
		return ResponseEntity.ok(bk.showBookingByEmail());
	}
}
