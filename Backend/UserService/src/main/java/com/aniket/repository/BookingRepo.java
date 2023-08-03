package com.aniket.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.aniket.model.Booking;

public interface BookingRepo extends MongoRepository<Booking, Integer> {
	
	Booking findAllByFirstName(String name);
	List<Booking> findAllByEmail(String email);

}
