package com.csse.eticket.controller;

import com.csse.eticket.dao.BusDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/bus")
public interface BusController {
    @PostMapping
    ResponseEntity<?> addBus(@RequestBody BusDao busDao);

    //updated to post mapping
    @PostMapping("/update")
    ResponseEntity<?> updateBus(@RequestBody BusDao busDao);

    //updated to post mapping
    @PostMapping("/delete")
    ResponseEntity<?> deleteBus(@RequestBody BusDao busDao);

    @GetMapping
    ResponseEntity<?> getAllBuses();

    @PostMapping("/find")
    ResponseEntity<?> findBus(@RequestBody BusDao busDao);

    @PostMapping("/add/{busNo}/{amount}")
    ResponseEntity<?> addAmount(@PathVariable String busNo, @PathVariable float amount);

}
