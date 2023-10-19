package com.csse.eticket.repository.users;

import com.csse.eticket.model.users.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

}

