package com.aniket.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.repository.TrainRepo;

@Service
public class TrainServiceImpl implements TrainServiceI {

	private static final Logger log = LoggerFactory.getLogger(TrainServiceImpl.class);

	@Autowired
	private TrainRepo trepo;
	
	@Autowired
	SequenceGeneratorService seq;

	@Override
	public TrainDetails addTrain(TrainDetails td) throws ResourceNotFoundException{
		TrainDetails check = trepo.findByTrainNo(td.getTrainNo());
		if(check != null) {
			if(check.getTrainNo() == td.getTrainNo() && check.getBoardingStation() == td.getBoardingStation() && check.getDestination() == td.getDestination()) {
				throw new ResourceNotFoundException("Cannot insert duplicate record");
			}
		}
		td.setId(seq.getSequenceNum(TrainDetails.sequenceName));
		TrainDetails traindetails = trepo.save(td);
		return td;
	}
	

	@Override
	public List<TrainDetails> showTrains() {
		return trepo.findAll();
	}

	@Override
	public TrainDetails updateTrain(int id, String destination) throws ResourceNotFoundException {
		Optional<TrainDetails> td = trepo.findById(id);
		if(td.isEmpty()) {
			log.warn("Train with the specified Id is not available" +id);
			throw new ResourceNotFoundException("Train with the following Id Does not Exist " +id);
		}
		TrainDetails tds = td.get();
		tds.setDestination(destination);
		TrainDetails save = trepo.save(tds);
		return save;
	}

	@Override
	public String deleteTrain(int id) throws ResourceNotFoundException {
		Optional<TrainDetails> td = trepo.findById(id);
		if(td.isEmpty()) {
			throw new ResourceNotFoundException("Train with the following Id Does not Exist " +id);
		}
		TrainDetails tds = td.get();
		trepo.delete(tds);
		return "Deleted Successfully";
	}


	@Override
	public TrainDetails updateTrain(int id, TrainDetails train) {
		Optional<TrainDetails> t = trepo.findById(id);
		TrainDetails tr = t.get();
		tr.setName(train.getName());
		tr.setTrainNo(train.getTrainNo());
		tr.setBoardingStation(train.getBoardingStation());
		tr.setDestination(train.getDestination());
		tr.setDate(train.getDate());
		tr.setFair(train.getFair());
		tr.setTiming(train.getTiming());
		tr.setTicketsAvailable(train.getTicketsAvailable());
		trepo.save(tr);
		return tr;
	}


	@Override
	public TrainDetails findTrainById(int id) {
		Optional<TrainDetails> ts = trepo.findById(id);
		return ts.get();
	}

}
