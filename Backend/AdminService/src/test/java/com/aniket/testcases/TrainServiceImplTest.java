package com.aniket.testcases;
import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.repository.TrainRepo;
import com.aniket.service.SequenceGeneratorService;
import com.aniket.service.TrainServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class TrainServiceImplTest {


@Mock
private TrainRepo trainRepo;

@Mock
private SequenceGeneratorService sequenceGeneratorService;

@InjectMocks
private TrainServiceImpl trainService;

@BeforeEach
public void setUp() {
    MockitoAnnotations.openMocks(this);
}

@Test
public void testAddTrain() throws ResourceNotFoundException {
    TrainDetails trainDetails = new TrainDetails();
    when(sequenceGeneratorService.getSequenceNum(TrainDetails.sequenceName)).thenReturn(1);
    when(trainRepo.save(trainDetails)).thenReturn(trainDetails);

    TrainDetails result = trainService.addTrain(trainDetails);

    assertEquals(1L, result.getId());
    assertEquals(trainDetails, result);

    verify(sequenceGeneratorService, times(1)).getSequenceNum(TrainDetails.sequenceName);
    verify(trainRepo, times(1)).save(trainDetails);
}

@Test
public void testShowTrains() {
    List<TrainDetails> trainList = new ArrayList<>();
    trainList.add(new TrainDetails());

    when(trainRepo.findAll()).thenReturn(trainList);

    List<TrainDetails> result = trainService.showTrains();

    assertEquals(trainList, result);

    verify(trainRepo, times(1)).findAll();
}

@Test
public void testUpdateTrain() throws ResourceNotFoundException {
    int id = 1;
    String destination = "New Destination";
    TrainDetails trainDetails = new TrainDetails();
    Optional<TrainDetails> optionalTrainDetails = Optional.of(trainDetails);

    when(trainRepo.findById(id)).thenReturn(optionalTrainDetails);
    when(trainRepo.save(trainDetails)).thenReturn(trainDetails);

    TrainDetails result = trainService.updateTrain(id, destination);

    assertEquals(destination, result.getDestination());

    verify(trainRepo, times(1)).findById(id);
    verify(trainRepo, times(1)).save(trainDetails);
}

@Test
public void testUpdateTrainNotFound() {
    int id = 1;
    String destination = "New Destination";

    when(trainRepo.findById(id)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
            () -> trainService.updateTrain(id, destination));

    assertEquals("Train with the following Id Does not Exist " + id, exception.getMessage());

    verify(trainRepo, times(1)).findById(id);
    verify(trainRepo, never()).save(any(TrainDetails.class));
}

@Test
public void testDeleteTrain() throws ResourceNotFoundException {
    int id = 1;
    TrainDetails trainDetails = new TrainDetails();
    Optional<TrainDetails> optionalTrainDetails = Optional.of(trainDetails);

    when(trainRepo.findById(id)).thenReturn(optionalTrainDetails);

    String result = trainService.deleteTrain(id);

    assertEquals("Deleted Successfully", result);

    verify(trainRepo, times(1)).findById(id);
    verify(trainRepo, times(1)).delete(trainDetails);
}

@Test
public void testDeleteTrainNotFound() {
    int id = 1;

    when(trainRepo.findById(id)).thenReturn(Optional.empty());

    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class,
            () -> trainService.deleteTrain(id));

    assertEquals("Train with the following Id Does not Exist " + id, exception.getMessage());

    verify(trainRepo, times(1)).findById(id);
    verify(trainRepo, never()).delete(any(TrainDetails.class));
}

@Test
public void testUpdateTrainDetails() {
    int id = 1;
    TrainDetails trainDetails = new TrainDetails();
    Optional<TrainDetails> optionalTrainDetails = Optional.of(trainDetails);

    when(trainRepo.findById(id)).thenReturn(optionalTrainDetails);
    when(trainRepo.save(trainDetails)).thenReturn(trainDetails);

    TrainDetails result = trainService.updateTrain(id, trainDetails);

    assertEquals(trainDetails, result);

    verify(trainRepo, times(1)).findById(id);
    verify(trainRepo, times(1)).save(trainDetails);
}

@Test
public void testFindTrainById() {
    int id = 1;
    TrainDetails trainDetails = new TrainDetails();
    Optional<TrainDetails> optionalTrainDetails = Optional.of(trainDetails);

    when(trainRepo.findById(id)).thenReturn(optionalTrainDetails);

    TrainDetails result = trainService.findTrainById(id);

    assertEquals(trainDetails, result);

    verify(trainRepo, times(1)).findById(id);
}
}