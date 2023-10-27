package com.csse.eticket.serviceImpl;

import com.csse.eticket.dao.users.AccDao;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSignUp() {
        AccDao accDao = new AccDao();
        accDao.setName("John Doe");
        accDao.setContactNumber("1234567890");
        accDao.setEmail("johndoe@example.com");
        accDao.setPassword("password123");
        accDao.setRole("ROLE_USER");
        accDao.setUniqueField("uniqueField");

        Mockito.when(userRepository.findByEmailId(Mockito.anyString())).thenReturn(null);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("hashedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(new User());

        ResponseEntity<?> response = userService.signUp(accDao);

        assertEquals(200, response.getStatusCodeValue());
    }
}
