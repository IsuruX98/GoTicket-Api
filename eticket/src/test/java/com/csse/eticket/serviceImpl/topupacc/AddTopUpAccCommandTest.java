package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.TopUpAccCommand;
import com.csse.eticket.serviceImpl.TopUpFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AddTopUpAccCommandTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private TopUpAccRepository topUpAccRepository;

    @Mock
    private TopUpAccDao topUpAccDao;

    @Mock
    private TopUpFactory topUpFactory;

    @InjectMocks
    private AddTopUpAccCommand addTopUpAccCommand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPositiveCase_ValidTopUpAccAddition() {
        // Mocked data
        TopUpAcc mockTopUpAcc = new TopUpAcc();
        User mockUser = new User();

        when(topUpAccDao.getType()).thenReturn("Family");
        JwtFilter jwtFilter = Mockito.mock(JwtFilter.class);
        when(jwtFilter.getCurrentUser()).thenReturn("john@example.com");
        when(userRepository.findByEmailId("john@example.com")).thenReturn(mockUser);
        when(topUpFactory.createTopUp("Family")).thenReturn(mockTopUpAcc);
        when(topUpAccRepository.save(Mockito.any(TopUpAcc.class))).thenReturn(mockTopUpAcc);

        ResponseEntity<?> response = addTopUpAccCommand.execute();

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        // Additional checks for the response content can be added here.
    }

    @Test
    public void testNegativeCase_ExceptionThrown() {
        when(topUpAccDao.getType()).thenReturn("Family");
        JwtFilter jwtFilter = Mockito.mock(JwtFilter.class);
        when(jwtFilter.getCurrentUser()).thenReturn("john@example.com");
        when(userRepository.findByEmailId("john@example.com")).thenThrow(new RuntimeException("Test Exception"));

        ResponseEntity<?> response = addTopUpAccCommand.execute();

        assertEquals(500, response.getStatusCodeValue()); // Internal Server Error
        assertEquals("Something went wrong", response.getBody());
        // Additional checks for the response content can be added here.
    }
}
