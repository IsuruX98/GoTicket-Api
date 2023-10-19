package com.csse.eticket.controller;

import com.csse.eticket.dao.TopUpAccDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/top-up-acc")
public interface TopUpAccController {
    @PostMapping
    ResponseEntity<?> addTopUpAcc(@RequestBody TopUpAccDao topUpAccDao);

    @DeleteMapping
    ResponseEntity<?> deleteTopUpAcc(@RequestBody TopUpAccDao topUpAccDao);

    @GetMapping
    ResponseEntity<?> getAllTopUpAcca();

    @PostMapping("/find")
    ResponseEntity<?> findTopUpAcc(@RequestBody TopUpAccDao topUpAccDao);

    @GetMapping("/user")
    ResponseEntity<?> getTopUpAccountsByUser();


}
