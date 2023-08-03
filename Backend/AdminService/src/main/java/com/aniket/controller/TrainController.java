package com.aniket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.service.TrainServiceImpl;

@CrossOrigin
@RestController
public class TrainController {
	
	@Autowired
	TrainServiceImpl ts;
	
	@PostMapping("/add")
	public ResponseEntity<?> addTrain(@RequestBody TrainDetails td)throws ResourceNotFoundException{
		TrainDetails save = ts.addTrain(td);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("/show")
	public ResponseEntity<?> showTrain(){
		return ResponseEntity.ok(ts.showTrains());
	}
	
	
	@GetMapping("/findbyid/{id}")
	public ResponseEntity<?> showById(@PathVariable int id){
		return ResponseEntity.ok(ts.findTrainById(id));
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteTrain(@PathVariable int id) throws ResourceNotFoundException{
		return ts.deleteTrain(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateTrain(@PathVariable int id,@RequestBody TrainDetails td){
		TrainDetails save = ts.updateTrain(id, td);
		return ResponseEntity.ok(save);
	}

}
