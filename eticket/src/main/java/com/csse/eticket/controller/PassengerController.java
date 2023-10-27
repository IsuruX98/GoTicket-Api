package com.csse.eticket.controller;


import com.csse.eticket.model.users.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/passenger")
public interface PassengerController {
    @GetMapping("/get")
    List<User> getPassengers();

    @GetMapping("/")
    ResponseEntity<?> getUser();

    @GetMapping("/acc-balance/{id}")
    ResponseEntity<?> getAccountBalance(@PathVariable Integer id);
}
