package com.aniket.testcases;

import com.aniket.controller.TrainController;
import com.aniket.exception.ResourceNotFoundException;
import com.aniket.model.TrainDetails;
import com.aniket.service.TrainServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TrainControllerTest {

    @Mock
    private TrainServiceImpl trainService;

    @InjectMocks
    private TrainController trainController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddTrain() throws ResourceNotFoundException {
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.addTrain(trainDetails)).thenReturn(trainDetails);

        ResponseEntity<?> responseEntity = trainController.addTrain(trainDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trainDetails, responseEntity.getBody());

        verify(trainService, times(1)).addTrain(trainDetails);
    }

    @Test
    public void testShowTrain() {
        List<TrainDetails> trainList = new ArrayList<>();
        when(trainService.showTrains()).thenReturn(trainList);

        ResponseEntity<?> responseEntity = trainController.showTrain();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trainList, responseEntity.getBody());

        verify(trainService, times(1)).showTrains();
    }

    @Test
    public void testShowById() throws ResourceNotFoundException {
        int id = 1;
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.findTrainById(id)).thenReturn(trainDetails);

        ResponseEntity<?> responseEntity = trainController.showById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trainDetails, responseEntity.getBody());

        verify(trainService, times(1)).findTrainById(id);
    }

    @Test
    public void testDeleteTrain() throws ResourceNotFoundException {
        int id = 1;
        when(trainService.deleteTrain(id)).thenReturn("Deleted Successfully");

        String result = trainController.deleteTrain(id);

        assertEquals("Deleted Successfully", result);

        verify(trainService, times(1)).deleteTrain(id);
    }

    @Test
    public void testUpdateTrain() {
        int id = 1;
        TrainDetails trainDetails = new TrainDetails();
        when(trainService.updateTrain(id, trainDetails)).thenReturn(trainDetails);

        ResponseEntity<?> responseEntity = trainController.updateTrain(id, trainDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(trainDetails, responseEntity.getBody());

        verify(trainService, times(1)).updateTrain(id, trainDetails);
    }
}

