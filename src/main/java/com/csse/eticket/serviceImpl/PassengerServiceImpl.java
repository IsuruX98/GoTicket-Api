package com.csse.eticket.serviceImpl;

import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.users.PassengerRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.PassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtFilter jwtFilter;
    ModelMapper modelMapper;


    @Override
    public User getCurrentUser() {
        String email = jwtFilter.getCurrentUser();
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getPassengers() {
        return userRepository.getPassengers();
    }
}
