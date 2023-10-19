package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.AccountBalanceController;
import com.csse.eticket.service.PassengerService;
import com.csse.eticket.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
public class AccountBalanceControllerImpl implements AccountBalanceController {

    @Autowired
    PassengerService passengerService;


    @Override
    public ResponseEntity<?> deductAmount(Integer id, float balance) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(passengerService.deductAmount(id, balance));
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }


}
