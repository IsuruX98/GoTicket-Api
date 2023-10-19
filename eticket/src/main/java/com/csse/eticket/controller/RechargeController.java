package com.csse.eticket.controller;

import com.csse.eticket.dao.RechargeDao;
import com.csse.eticket.dao.TopUpAccDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/recharge")
public interface RechargeController {
    @PostMapping
    ResponseEntity<?> recharge(@RequestBody RechargeDao rechargeDao);

    @GetMapping
    ResponseEntity<?> getAllRecharges();

    @GetMapping("/user")
    ResponseEntity<?> getRechargesByUser();
}
