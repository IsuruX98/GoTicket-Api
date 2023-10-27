package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AddBusCommandTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BusRouteRepository routeRepository;

    @Mock
    private BusDao busDao;

    @InjectMocks
    private AddBusCommand addBusCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositiveCase_ValidBusAddition() {
        // Mocked data
        Bus mockBus = new Bus();
        User mockUser = new User();
        BusRoute mockRoute = new BusRoute();

        when(busDao.getBusNo()).thenReturn("BUS123");
        when(userRepository.findByEmailId("john@example.com")).thenReturn(mockUser);
        when(routeRepository.findByRouteName("Sample Route")).thenReturn(mockRoute);
        when(busRepository.save(Mockito.any(Bus.class))).thenReturn(mockBus);

        ResponseEntity<?> response = addBusCommand.execute();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testNegativeCase_InvalidUserEmail() {
        when(busDao.getBusNo()).thenReturn("BUS123");
        when(userRepository.findByEmailId("invalid@example.com")).thenReturn(null);

        ResponseEntity<?> response = addBusCommand.execute();

        assertEquals(404, response.getStatusCodeValue()); // User not found
        assertNull(response.getBody());
    }

    @Test
    public void testNegativeCase_InvalidRouteName() {
        when(busDao.getBusNo()).thenReturn("BUS123");
        when(userRepository.findByEmailId("john@example.com")).thenReturn(new User());
        when(routeRepository.findByRouteName("NonExistentRoute")).thenReturn(null);

        ResponseEntity<?> response = addBusCommand.execute();

        assertEquals(404, response.getStatusCodeValue()); // Route not found
        assertNull(response.getBody());
    }

    @Test
    public void testNegativeCase_MissingBusNo() {
        when(busDao.getBusNo()).thenReturn(null);

        ResponseEntity<?> response = addBusCommand.execute();

        assertEquals(400, response.getStatusCodeValue()); // Bad Request
        assertNull(response.getBody());
    }
}
