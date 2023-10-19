package com.csse.eticket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/transaction")
public interface TransactionController {

    @PostMapping("/trans/{amount}/{type}")
    ResponseEntity<?> addTransaction(@PathVariable float amount, @PathVariable String type);

    @GetMapping("")
    ResponseEntity<?> getAllTransactions();

}

