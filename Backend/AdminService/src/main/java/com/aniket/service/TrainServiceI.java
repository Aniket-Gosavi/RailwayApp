package com.aniket.service;

import java.util.List;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;

public interface TrainServiceI {
	TrainDetails addTrain(TrainDetails td) throws ResourceNotFoundException;
	List<TrainDetails> showTrains();
	TrainDetails updateTrain(int trainNo, String destination) throws ResourceNotFoundException;
	String deleteTrain(int id) throws ResourceNotFoundException;
	TrainDetails updateTrain(int id, TrainDetails train);
	TrainDetails findTrainById(int id);
}
