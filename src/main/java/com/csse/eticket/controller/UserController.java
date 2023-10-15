package com.csse.eticket.controller;

import com.csse.eticket.dao.users.AccDao;
import com.csse.eticket.model.users.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "/user")
public interface UserController {
    @PostMapping(path = "/signup")
    ResponseEntity<?> signUp(@RequestBody(required = true) AccDao accDao);

    @PostMapping(path = "/login")
    ResponseEntity<?> login(@RequestBody(required = true) User user);

    @GetMapping(path = "/get")
    ResponseEntity<?> getAllUser();

    @GetMapping(path = "/checkToken")
    ResponseEntity<?> checkToken();
}
