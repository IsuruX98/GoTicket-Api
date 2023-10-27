package com.csse.eticket.controller;

import com.csse.eticket.dao.BusSheduleDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/schedule")
public interface BusScheduleController {
    @PostMapping
    ResponseEntity<?> addSchedule(@RequestBody BusSheduleDao busSheduleDao);

    @PostMapping("/update")
    ResponseEntity<?> updateSchedule(@RequestBody BusSheduleDao busSheduleDao);

    @PostMapping("/delete")
    ResponseEntity<?> deleteSchedule(@RequestBody BusSheduleDao busSheduleDao);

    @GetMapping
    ResponseEntity<?> getAllSchedule();

    @PostMapping("/find")
    ResponseEntity<?> findSchedule(@RequestBody BusSheduleDao busSheduleDao);
}
