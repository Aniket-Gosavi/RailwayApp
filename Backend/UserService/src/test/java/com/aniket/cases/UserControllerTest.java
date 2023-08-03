//package com.aniket.cases;
//
//import com.aniket.controller.UserController;
//import com.aniket.exception.ResourceNotFoundException;
//import com.aniket.model.Booking;
//import com.aniket.model.TrainDetails;
//import com.aniket.model.TransactionDetails;
//import com.aniket.service.BookingService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.Mockito.*;
//
//public class UserControllerTest {
//
//    @Mock
//    private BookingService bookingService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testBookTrain() {
//        Booking booking = new Booking();
//        booking.setId(1);
//        booking.setFirstName("John");
//        booking.setLastName("Doe");
//
//        when(bookingService.bookTrain(booking)).thenReturn(booking);
//
//        ResponseEntity<?> response = userController.bookTrain(booking);
//
//        assertEquals(booking, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).bookTrain(booking);
//    }
//
//    @Test
//    public void testShowTrain() throws ResourceNotFoundException {
//        List<TrainDetails> trainDetailsList = new ArrayList<>();
//        trainDetailsList.add(new TrainDetails());
//
//        when(bookingService.showTrains()).thenReturn(trainDetailsList);
//
//        ResponseEntity<?> response = userController.showTrain();
//
//        assertEquals(trainDetailsList, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).showTrains();
//    }
//
//    @Test
//    public void testShowTrainEmptyList() throws ResourceNotFoundException {
//        when(bookingService.showTrains()).thenReturn(new ArrayList<>());
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//            userController.showTrain();
//        });
//
//        assertEquals("No Data Available to show", exception.getMessage());
//
//        verify(bookingService, times(1)).showTrains();
//    }
//
//    @Test
//    public void testFindById() throws ResourceNotFoundException {
//        int id = 1;
//        Booking booking = new Booking();
//        booking.setId(id);
//
//        when(bookingService.showBookingById(id)).thenReturn(booking);
//
//        ResponseEntity<Booking> response = userController.findById(id);
//
//        assertEquals(booking, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).showBookingById(id);
//    }
//
//    @Test
//    public void testFindByIdNotFound() throws ResourceNotFoundException {
//        int id = 1;
//
//        when(bookingService.showBookingById(id)).thenThrow(new ResourceNotFoundException("not found"));
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//            userController.findById(id);
//        });
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingService, times(1)).showBookingById(id);
//    }
//
//    @Test
//    public void testFindByName() throws ResourceNotFoundException {
//        String name = "John Doe";
//        Booking booking = new Booking();
//        booking.setFirstName(name);
//
//        when(bookingService.showBookingByName(name)).thenReturn(booking);
//
//        ResponseEntity<?> response = userController.findByName(name);
//
//        assertEquals(booking, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).showBookingByName(name);
//    }
//
//    @Test
//    public void testFindByNameNotFound() throws ResourceNotFoundException {
//        String name = "John Doe";
//
//        when(bookingService.showBookingByName(name)).thenThrow(new ResourceNotFoundException("not found"));
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//            userController.findByName(name);
//        });
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingService, times(1)).showBookingByName(name);
//    }
//
//    @Test
//    public void testFindBySourceAndDestination() throws ResourceNotFoundException {
//        String source = "Station A";
//        String destination = "Station B";
//        List<TrainDetails> trainDetailsList = new ArrayList<>();
//        trainDetailsList.add(new TrainDetails());
//
//        when(bookingService.showBySourceAndDestination(source, destination, destination)).thenReturn(trainDetailsList);
//
//        ResponseEntity<?> response = userController.findBySourceAndDestination(source, destination, destination);
//
//        assertEquals(trainDetailsList, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).showBySourceAndDestination(source, destination);
//    }
//
//    @Test
//    public void testFindBySourceAndDestinationNotFound() throws ResourceNotFoundException {
//        String source = "Station A";
//        String destination = "Station B";
//
//        when(bookingService.showBySourceAndDestination(source, destination)).thenThrow(new ResourceNotFoundException("not found"));
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//            userController.findBySourceAndDestination(source, destination);
//        });
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingService, times(1)).showBySourceAndDestination(source, destination);
//    }
//
//    @Test
//    public void testFindByDate() {
//        LocalDate date = LocalDate.now();
//        List<TrainDetails> trainDetailsList = new ArrayList<>();
//        trainDetailsList.add(new TrainDetails());
//
//        when(bookingService.showByDate(date)).thenReturn(trainDetailsList);
//
//        ResponseEntity<?> response = userController.findByDate(date);
//
//        assertEquals(trainDetailsList, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).showByDate(date);
//    }
//
//    @Test
//    public void testCancelBooking() throws ResourceNotFoundException {
//        int id = 1;
//        Booking booking = new Booking();
//        booking.setId(id);
//
//        when(bookingService.cancelTicket(id)).thenReturn(booking);
//
//        ResponseEntity<?> response = userController.cancelBooking(id);
//
//        assertEquals(booking, response.getBody());
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//
//        verify(bookingService, times(1)).cancelTicket(id);
//    }
//
//    @Test
//    public void testCancelBookingNotFound() throws ResourceNotFoundException {
//        int id = 1;
//
//        when(bookingService.cancelTicket(id)).thenThrow(new ResourceNotFoundException("not found"));
//
//        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
//            userController.cancelBooking(id);
//        });
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingService, times(1)).cancelTicket(id);
//    }
//
//}