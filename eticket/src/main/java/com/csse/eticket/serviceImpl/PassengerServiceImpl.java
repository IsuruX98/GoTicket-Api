package com.csse.eticket.serviceImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.PassengerRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PassengerServiceImpl implements PassengerService {

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopUpAccRepository topUpAccRepository;

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


    @Override
    public ResponseEntity<?> deductAmount(Integer id, float balance) {
        try{
            TopUpAcc topUpAcc = topUpAccRepository.findByUserId(id);
            topUpAcc.setBalance(topUpAcc.getBalance() - balance);
            topUpAccRepository.save(topUpAcc);
            return ResponseEntity.status(HttpStatus.OK).body(ETicketConstants.SUCCESSFULLY_DEDUCTED);
        }catch(Exception e){
            log.error(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(ETicketConstants.NOT_FOUND);
    }
}
