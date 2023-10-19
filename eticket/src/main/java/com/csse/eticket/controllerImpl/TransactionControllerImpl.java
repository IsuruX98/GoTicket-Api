package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.TransactionController;
import com.csse.eticket.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TransactionControllerImpl implements TransactionController {

    @Autowired
    TransactionService transactionService;
    @Override
    public ResponseEntity<?> addTransaction(float amount, String type) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(transactionService.AddTransaction(amount, type));
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public ResponseEntity<?> getAllTransactions() {
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.getAllTransactions());
    }

}
