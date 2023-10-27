package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.repository.BusRepository;
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

public class DeleteBusCommandTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private BusDao busDao;

    @InjectMocks
    private DeleteBusCommand deleteBusCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositiveCase_ValidBusDeletion() {
        // Mocked data
        Bus mockBus = new Bus();

        when(busDao.getBusNo()).thenReturn("BUS123");
        when(busRepository.findByBusNo("BUS123")).thenReturn(mockBus);

        ResponseEntity<?> response = deleteBusCommand.execute();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Bus Deleted", response.getBody());
    }

    @Test
    public void testNegativeCase_BusNotFound() {
        when(busDao.getBusNo()).thenReturn("NonExistentBusNo");
        when(busRepository.findByBusNo("NonExistentBusNo")).thenReturn(null);

        ResponseEntity<?> response = deleteBusCommand.execute();

        assertEquals(400, response.getStatusCodeValue()); // Bad Request
        assertEquals("Bus not found", response.getBody());
    }
}
