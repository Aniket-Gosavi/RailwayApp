package com.aniket.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "traindetails")
public class TrainDetails {

	@Id
	@NotNull
	private int id;
	@NotEmpty(message = "Name shoud not be empty")
	private String name;
	@NotEmpty(message = "TrainNo shoud not be empty")
	private int trainNo;
	private String boardingStation;
	private String destination;
	private String timing;
	private String date;
	private double fair;
    private int ticketsAvailable;
    

}
