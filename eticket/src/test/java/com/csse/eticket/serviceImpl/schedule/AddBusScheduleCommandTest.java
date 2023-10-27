package com.csse.eticket.serviceImpl.schedule;

import com.csse.eticket.dao.BusSheduleDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.model.BusSchedule;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.BusScheduleRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AddBusScheduleCommandTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private BusScheduleRepository busScheduleRepository;

    @Mock
    private BusRouteRepository routeRepository;

    @Mock
    private BusSheduleDao busSheduleDao;

    @InjectMocks
    private AddBusScheduleCommand addBusScheduleCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositiveCase_ValidBusScheduleAddition() {
        // Mocked data
        BusSchedule mockBusSchedule = new BusSchedule();
        BusRoute mockRoute = new BusRoute();
        Bus mockBus = new Bus();

        when(busSheduleDao.getDate()).thenReturn("2023-10-16");
        when(busSheduleDao.getStartTime()).thenReturn("08:00:00");
        when(busSheduleDao.getEndTime()).thenReturn("10:00:00");
        when(busSheduleDao.getRouteName()).thenReturn("Sample Route");
        when(busSheduleDao.getBusNo()).thenReturn("BUS123");

        when(routeRepository.findByRouteName("Sample Route")).thenReturn(mockRoute);
        when(busRepository.findByBusNo("BUS123")).thenReturn(mockBus);
        when(busScheduleRepository.save(Mockito.any(BusSchedule.class))).thenReturn(mockBusSchedule);

        ResponseEntity<?> response = addBusScheduleCommand.execute();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void testNegativeCase_InvalidRouteName() {
        when(busSheduleDao.getDate()).thenReturn("2023-10-16");
        when(busSheduleDao.getStartTime()).thenReturn("08:00:00");
        when(busSheduleDao.getEndTime()).thenReturn("10:00:00");
        when(busSheduleDao.getRouteName()).thenReturn("NonExistentRoute");
        when(busSheduleDao.getBusNo()).thenReturn("BUS123");

        when(routeRepository.findByRouteName("NonExistentRoute")).thenReturn(null);

        ResponseEntity<?> response = addBusScheduleCommand.execute();

        assertEquals(404, response.getStatusCodeValue()); // Route not found
        assertNull(response.getBody());
    }

    @Test
    public void testNegativeCase_InvalidBusNo() {
        when(busSheduleDao.getDate()).thenReturn("2023-10-16");
        when(busSheduleDao.getStartTime()).thenReturn("08:00:00");
        when(busSheduleDao.getEndTime()).thenReturn("10:00:00");
        when(busSheduleDao.getRouteName()).thenReturn("Sample Route");
        when(busSheduleDao.getBusNo()).thenReturn("NonExistentBusNo");

        when(routeRepository.findByRouteName("Sample Route")).thenReturn(new BusRoute());
        when(busRepository.findByBusNo("NonExistentBusNo")).thenReturn(null);

        ResponseEntity<?> response = addBusScheduleCommand.execute();

        assertEquals(404, response.getStatusCodeValue()); // Bus not found
        assertNull(response.getBody());
    }
}
