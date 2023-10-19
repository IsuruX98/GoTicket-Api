package com.csse.eticket.serviceImpl;

import com.csse.eticket.model.users.*;
import org.springframework.stereotype.Service;

@Service
public class UserFactory {
    public User createUser(String role) {
        switch (role.toLowerCase()) {
            case "manager":
                return new Manager();
            case "inspector":
                return new Inspector();
            case "passenger":
                return new Passenger();
            case "foreigner":
                return new Foreigner();
            default:
                throw new IllegalArgumentException("Invalid role");
        }
    }
}
