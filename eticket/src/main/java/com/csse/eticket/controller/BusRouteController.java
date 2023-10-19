package com.csse.eticket.controller;

import com.csse.eticket.dao.BusRouteDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/route")
public interface BusRouteController {
    @PostMapping
    ResponseEntity<?> addBusRoute(@RequestBody BusRouteDao busRouteDao);

    @PutMapping
    ResponseEntity<?> updateBusRoute(@RequestBody BusRouteDao busRouteDao);

    @DeleteMapping
    ResponseEntity<?> deleteBusRoute(@RequestBody BusRouteDao busRouteDao);

    @GetMapping
    ResponseEntity<?> getAllBusRoutes();

    @PostMapping("/find")
    ResponseEntity<?> findBusRoute(@RequestBody BusRouteDao busRouteDao);
}
