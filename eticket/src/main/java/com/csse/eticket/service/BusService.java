package com.csse.eticket.service;

import com.csse.eticket.model.Bus;
import org.springframework.http.ResponseEntity;

public interface BusService {

    Bus addAmount(String busNo, float amount);
}
