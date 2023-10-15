package com.csse.eticket.controllerImpl;


import com.csse.eticket.controller.PassengerController;
import com.csse.eticket.model.users.User;
import com.csse.eticket.service.PassengerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
@Transactional
public class PassengerControllerImpl implements PassengerController {

    @Autowired
    PassengerService passengerService;

    @Override
    public List<User> getPassengers() {
        return passengerService.getPassengers();
    }

    @Override
    public ResponseEntity<?> getUser(){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(passengerService.getCurrentUser());
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return  ResponseEntity.internalServerError().body("SOMETHING_WENT_WRONG");
    }
}
