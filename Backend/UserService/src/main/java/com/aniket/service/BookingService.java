package com.aniket.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.Booking;
import com.aniket.model.TrainDetails;
import com.razorpay.RazorpayException;

public interface BookingService {
	List<TrainDetails> showTrains();
	Booking bookTrain(Booking book);
	Booking showBookingById(int id)throws ResourceNotFoundException;
	Booking showBookingByName(String name) throws ResourceNotFoundException;
	List<TrainDetails> showBySourceAndDestination(String source, String destination,String date) throws ResourceNotFoundException;
	List<TrainDetails> showByDate(LocalDate date);
	Booking cancelTicket(int id)throws ResourceNotFoundException;
}
