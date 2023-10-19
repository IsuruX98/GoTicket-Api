package com.csse.eticket.service;



import com.csse.eticket.model.users.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PassengerService {

   User getCurrentUser();

    List<User> getPassengers();

    Object deductAmount(Integer id, float balance);
}
