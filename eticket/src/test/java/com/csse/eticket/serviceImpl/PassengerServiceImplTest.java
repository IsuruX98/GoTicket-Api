package com.csse.eticket.serviceImpl;

import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.PassengerRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.jwt.JwtFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

class PassengerServiceImplTest {

    @InjectMocks
    private PassengerServiceImpl passengerService;

    @Mock
    private PassengerRepository passengerRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TopUpAccRepository topUpAccRepository;

    @Mock
    private JwtFilter jwtFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetCurrentUser() {
        // Prepare a sample user
        User sampleUser = new User();
        sampleUser.setEmail("sample@example.com");

        // Mock the behavior of JwtFilter
        when(jwtFilter.getCurrentUser()).thenReturn("sample@example.com");

        // Mock the behavior of UserRepository to return the sample user
        when(userRepository.findByEmail("sample@example.com")).thenReturn(sampleUser);

        User currentUser = passengerService.getCurrentUser();

        // Verify that the service method correctly returns the current user
        assert currentUser != null;
        assert currentUser.getEmail().equals("sample@example.com");
    }

    @Test
    void testDeductAmount() {
        // Prepare a sample top-up account
        TopUpAcc topUpAcc = new TopUpAcc();
        topUpAcc.setId(1);
        topUpAcc.setBalance(100.0f);

        // Mock the behavior of TopUpAccRepository to return the sample top-up account
        when(topUpAccRepository.findByUserId(1)).thenReturn(topUpAcc);

        ResponseEntity<?> response = passengerService.deductAmount(1, 50.0f);

        // Verify that the balance was correctly deducted and a success response is returned
        assert response != null;
        assert response.getStatusCode() == HttpStatus.OK;
        assert response.getBody().equals("Successfully deducted");

        // Verify that the top-up account was updated
        assert topUpAcc.getBalance() == 50.0f;
    }
}
