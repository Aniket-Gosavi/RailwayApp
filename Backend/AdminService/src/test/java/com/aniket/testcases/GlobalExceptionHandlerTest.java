package com.aniket.testcases;

import com.aniket.exception.ErrorDetails;
import com.aniket.exception.GlobalExceptionHandler;
import com.aniket.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testResourceNotFoundExceptionHandler() {
        ResourceNotFoundException exception = new ResourceNotFoundException("Resource not found");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> responseEntity = globalExceptionHandler.resourceNotFoundException(exception, request);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals(new Date().getTime(), errorDetails.getTimestamp().getTime());
        assertEquals("Resource not found", errorDetails.getMessage());
        assertEquals(request.getDescription(false), errorDetails.getDetails());
    }

    @Test
    public void testGenericExceptionHandler() {
        Exception exception = new Exception("Internal Server Error");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> responseEntity = globalExceptionHandler.globleExcpetionHandler(exception, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorDetails errorDetails = (ErrorDetails) responseEntity.getBody();
        assertEquals(new Date().getTime(), errorDetails.getTimestamp().getTime());
        assertEquals("Internal Server Error", errorDetails.getMessage());
        assertEquals(request.getDescription(false), errorDetails.getDetails());
    }
}


