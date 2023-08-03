package com.aniket.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passengers {
	
	private String firstName;
	private String lastName;
	private String Category;
	private String gender;
	

}
