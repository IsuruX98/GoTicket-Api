package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class AddBusRouteCommandTest {

    @Mock
    private BusRouteRepository busRouteRepository;

    @Mock
    private BusRouteDao busRouteDao;

    @InjectMocks
    private AddBusRouteCommand addBusRouteCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executeWithValidInput() {
        // Arrange
        when(busRouteDao.getRouteName()).thenReturn("RouteName");
        when(busRouteDao.getStartPoint()).thenReturn("StartPoint");
        when(busRouteDao.getEndPoint()).thenReturn("EndPoint");
        when(busRouteDao.getTicketPrice()).thenReturn(50.0);

        BusRoute expectedBusRoute = new BusRoute();
        expectedBusRoute.setRouteName("RouteName");
        expectedBusRoute.setStartPoint("StartPoint");
        expectedBusRoute.setEndPoint("EndPoint");
        expectedBusRoute.setTicketPrice(50.0);

        when(busRouteRepository.save(expectedBusRoute)).thenReturn(expectedBusRoute);

        // Act
        ResponseEntity<?> responseEntity = addBusRouteCommand.execute();
        BusRoute actualBusRoute = (BusRoute) responseEntity.getBody();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedBusRoute, actualBusRoute);
    }

    @Test
    void executeWithInvalidInput() {
        // Arrange
        when(busRouteDao.getRouteName()).thenReturn(null); // Invalid RouteName
        when(busRouteDao.getStartPoint()).thenReturn("StartPoint");
        when(busRouteDao.getEndPoint()).thenReturn("EndPoint");
        when(busRouteDao.getTicketPrice()).thenReturn(50.0);

        // Act
        ResponseEntity<?> responseEntity = addBusRouteCommand.execute();

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue()); // Expecting Bad Request status code
        assertNull(responseEntity.getBody()); // Expecting null response body for invalid input
    }
}
