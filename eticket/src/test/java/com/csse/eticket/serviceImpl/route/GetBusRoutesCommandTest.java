package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class GetBusRoutesCommandTest {

    @Mock
    private BusRouteRepository busRouteRepository;

    @InjectMocks
    private GetBusRoutesCommand getBusRoutesCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeWithValidData() {
        // Arrange
        BusRoute busRoute1 = new BusRoute("Route1", "Start1", "End1", 50.0);
        BusRoute busRoute2 = new BusRoute("Route2", "Start2", "End2", 60.0);
        List<BusRoute> expectedBusRoutes = Arrays.asList(busRoute1, busRoute2);

        when(busRouteRepository.findAll()).thenReturn(expectedBusRoutes);

        // Act
        ResponseEntity<?> responseEntity = getBusRoutesCommand.execute();
        List<BusRoute> actualBusRoutes = (List<BusRoute>) responseEntity.getBody();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedBusRoutes, actualBusRoutes);
    }

    @Test
    void executeWithEmptyData() {
        // Arrange
        when(busRouteRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<?> responseEntity = getBusRoutesCommand.execute();
        List<BusRoute> actualBusRoutes = (List<BusRoute>) responseEntity.getBody();

        // Assert
        assertEquals(404, responseEntity.getStatusCodeValue()); // Expecting Not Found status code
        assertNull(actualBusRoutes); // Expecting null response body for empty data
    }
}
