package com.aniket.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Document(collection = "Booking")
@Data
public class Booking {

	@Transient
	public static final String sequenceName = "BookingSequence";

	@Id
	@NotEmpty
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String firstName;
	private String lastName;
	@NotEmpty(message = "Email shoud not be empty")
	private String email;
	private int trainNo;
	@NotEmpty(message = "Travellers shoud not be empty")
	private int numberOfTravellers;
	private double amount;
	private LocalDate date;
	private List<Passengers> passList; 

}
