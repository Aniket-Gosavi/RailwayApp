//package com.aniket.cases;
//import com.aniket.exception.ResourceNotFoundException;
//import com.aniket.model.Booking;
//import com.aniket.model.TrainDetails;
//import com.aniket.repository.BookingRepo;
//import com.aniket.repository.TrainRepo;
//import com.aniket.service.BookingService;
//import com.aniket.service.BookingServiceImpl;
//import com.aniket.service.SequenceGeneratorService;
//import mailservice.EmailServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//public class BookingServiceImplTest {
//
//    @Mock
//    private BookingRepo bookingRepo;
//
//    @Mock
//    private TrainRepo trainRepo;
//
//    @Mock
//    private EmailServiceImpl emailService;
//
//    @Mock
//    private SequenceGeneratorService sequenceGeneratorService;
//
//    @InjectMocks
//    private BookingServiceImpl bookingService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testShowTrains() {
//        List<TrainDetails> trainList = new ArrayList<>();
//        trainList.add(new TrainDetails());
//
//        when(trainRepo.findAll()).thenReturn(trainList);
//
//        List<TrainDetails> result = bookingService.showTrains();
//
//        assertEquals(trainList, result);
//
//        verify(trainRepo, times(1)).findAll();
//    }
//
//    @Test
//    public void testShowBookingById() throws ResourceNotFoundException {
//        int id = 1;
//        Booking booking = new Booking();
//
//        when(bookingRepo.findById(id)).thenReturn(Optional.of(booking));
//
//        Booking result = bookingService.showBookingById(id);
//
//        assertEquals(booking, result);
//
//        verify(bookingRepo, times(1)).findById(id);
//    }
//
//    @Test
//    public void testShowBookingByIdNotFound() {
//        int id = 1;
//
//        when(bookingRepo.findById(id)).thenReturn(Optional.empty());
//
//        ResourceNotFoundException exception = assertThrows(
//                ResourceNotFoundException.class,
//                () -> bookingService.showBookingById(id)
//        );
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingRepo, times(1)).findById(id);
//    }
//
//    @Test
//    public void testShowBookingByName() throws ResourceNotFoundException {
//        String name = "John Doe";
//        Booking booking = new Booking();
//
//        when(bookingRepo.findAllByFirstName(name)).thenReturn(booking);
//
//        Booking result = bookingService.showBookingByName(name);
//
//        assertEquals(booking, result);
//
//        verify(bookingRepo, times(1)).findAllByFirstName(name);
//    }
//
//    @Test
//    public void testShowBookingByNameNotFound() {
//        String name = "John Doe";
//
//        when(bookingRepo.findAllByFirstName(name)).thenReturn(null);
//
//        ResourceNotFoundException exception = assertThrows(
//                ResourceNotFoundException.class,
//                () -> bookingService.showBookingByName(name)
//        );
//
//        assertEquals("not found", exception.getMessage());
//
//        verify(bookingRepo, times(1)).findAllByFirstName(name);
//    }
//
//    @Test
//    public void testBookTrain() {
//        Booking booking = new Booking();
//        booking.setTrainNo(123);
//        TrainDetails trainDetails = new TrainDetails();
//        trainDetails.setFair(10.0);
//        trainDetails.setTicketsAvailable(8);
//
//        when(trainRepo.findByTrainNo(booking.getTrainNo())).thenReturn(trainDetails);
//        when(sequenceGeneratorService.getSequenceNum(Booking.sequenceName)).thenReturn(1);
//        when(bookingRepo.save(booking)).thenReturn(booking);
//
//        Booking result = bookingService.bookTrain(booking);
//
//        assertEquals(1L, result.getId());
//        assertEquals(100.0, result.getFair());
//        assertEquals(7, trainDetails.getTicketsAvailable());
//
//        verify(trainRepo, times(1)).findByTrainNo(booking.getTrainNo());
//        verify(sequenceGeneratorService, times(1)).getSequenceNum(Booking.sequenceName);
//        verify(bookingRepo, times(1)).save(booking);
//        verify(trainRepo, times(1)).save(trainDetails);
//    }
//
//    @Test
//    public void testShowBySourceAndDestination() throws ResourceNotFoundException {
//        String source = "Station A";
//        String destination = "Station B";
//        List<TrainDetails> trainList = new ArrayList<>();
//        trainList.add(new TrainDetails());
//
//        when(trainRepo.findByBoardingStationAndDestination(source, destination)).thenReturn(trainList);
//
//        List<TrainDetails> result = bookingService.showBySourceAndDestination(source, destination);
//
//        assertEquals(trainList, result);
//
//        verify(trainRepo, times(1)).findByBoardingStationAndDestination(source, destination);
//    }
//
//    // Write more test methods for the remaining functionality of BookingServiceImpl
//
//}
