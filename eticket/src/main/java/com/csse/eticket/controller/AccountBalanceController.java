package com.csse.eticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/balance")
public interface AccountBalanceController {

    @PutMapping("/deduct/{id}/{balance}")
    ResponseEntity<?> deductAmount(@PathVariable Integer id, @PathVariable float balance);


}
