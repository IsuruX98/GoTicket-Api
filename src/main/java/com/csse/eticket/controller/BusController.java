package com.csse.eticket.controller;

import com.csse.eticket.dao.BusDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bus")
public interface BusController {
    @PostMapping
    ResponseEntity<?> addBus(@RequestBody BusDao busDao);

    @PutMapping
    ResponseEntity<?> updateBus(@RequestBody BusDao busDao);

    @DeleteMapping
    ResponseEntity<?> deleteBus(@RequestBody BusDao busDao);

    @GetMapping
    ResponseEntity<?> getAllBuses();

    @PostMapping("/find")
    ResponseEntity<?> findBus(@RequestBody BusDao busDao);
}
