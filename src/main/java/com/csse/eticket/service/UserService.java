package com.csse.eticket.service;

import com.csse.eticket.dao.users.AccDao;
import com.csse.eticket.model.users.User;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface UserService {
    ResponseEntity<?> signUp(AccDao accDao);

    ResponseEntity<?> login(User user);

    ResponseEntity<?> getAllUser();

    ResponseEntity<?> checkToken();

    ResponseEntity<?> changePassword(Map<String , String> requestMap);
}
