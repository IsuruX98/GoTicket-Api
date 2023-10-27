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
import static org.mockito.Mockito.*;

class DeleteBusRouteCommandTest {

    @Mock
    private BusRouteRepository busRouteRepository;

    @Mock
    private BusRouteDao busRouteDao;

    @InjectMocks
    private DeleteBusRouteCommand deleteBusRouteCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void execute_WhenBusRouteExists_ShouldReturnSuccessMessage() {
        // Arrange
        String routeName = "RouteName";
        BusRoute existingBusRoute = new BusRoute(routeName, "Start", "End", 50.0);

        when(busRouteDao.getRouteName()).thenReturn(routeName);
        when(busRouteRepository.findByRouteName(routeName)).thenReturn(existingBusRoute);

        // Act
        ResponseEntity<?> responseEntity = deleteBusRouteCommand.execute();

        // Assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Bus Route Deleted", responseEntity.getBody());

        // Verify that deleteById was called with the correct routeName
        verify(busRouteRepository, times(1)).deleteById(routeName);
    }

    @Test
    void execute_WhenBusRouteDoesNotExist_ShouldReturnBadRequest() {
        // Arrange
        String nonExistentRouteName = "NonExistentRoute";
        when(busRouteDao.getRouteName()).thenReturn(nonExistentRouteName);
        when(busRouteRepository.findByRouteName(nonExistentRouteName)).thenReturn(null);

        // Act
        ResponseEntity<?> responseEntity = deleteBusRouteCommand.execute();

        // Assert
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Bus Route not found", responseEntity.getBody());

        // Verify that deleteById was not called for a non-existent route
        verify(busRouteRepository, never()).deleteById(any());
    }
}
