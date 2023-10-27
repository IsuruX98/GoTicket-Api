package com.csse.eticket.serviceImpl.recharge;

import com.csse.eticket.dao.RechargeDao;
import com.csse.eticket.model.Recharge;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.RechargeRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AddRechargeCommandTest {

    @Mock
    private TopUpAccRepository topUpAccRepository;

    @Mock
    private RechargeRepository rechargeRepository;

    @Mock
    private RechargeDao rechargeDao;

    @InjectMocks
    private AddRechargeCommand addRechargeCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositiveCase_ValidRecharge() {
        // Mocked data
        TopUpAcc mockTopUpAcc = new TopUpAcc();
        Recharge mockRecharge = new Recharge();

        when(rechargeDao.getId()).thenReturn(1);
        when(topUpAccRepository.findByAccId(1)).thenReturn(mockTopUpAcc);
        when(rechargeDao.getAmount()).thenReturn(50.0F);
        when(rechargeRepository.save(Mockito.any(Recharge.class))).thenReturn(mockRecharge);

        ResponseEntity<?> response = addRechargeCommand.execute();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        // Additional checks for the response content can be added here.
    }

    @Test
    public void testNegativeCase_TopUpAccNotFound() {
        when(rechargeDao.getId()).thenReturn(1);
        when(topUpAccRepository.findByAccId(1)).thenReturn(null);

        ResponseEntity<?> response = addRechargeCommand.execute();

        assertEquals(500, response.getStatusCodeValue()); // Internal Server Error
        assertEquals("Something went wrong", response.getBody());
        // Additional checks for the response content can be added here.
    }

    @Test
    public void testNegativeCase_ExceptionThrown() {
        when(rechargeDao.getId()).thenReturn(1);
        when(topUpAccRepository.findByAccId(1)).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<?> response = addRechargeCommand.execute();

        assertEquals(500, response.getStatusCodeValue()); // Internal Server Error
        assertEquals("Something went wrong", response.getBody());
        // Additional checks for the response content can be added here.
    }
}
