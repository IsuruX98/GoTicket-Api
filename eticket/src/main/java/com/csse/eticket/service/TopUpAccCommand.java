package com.csse.eticket.service;

import org.springframework.http.ResponseEntity;

public interface TopUpAccCommand {
    ResponseEntity<?> execute();
}
