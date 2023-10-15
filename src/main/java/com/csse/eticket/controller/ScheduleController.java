package com.csse.eticket.controller;

import com.csse.eticket.dao.ScheduleDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/schedules")
public interface ScheduleController {

    @GetMapping
    ResponseEntity<List<ScheduleDAO>> getAllSchedules();

    @GetMapping("/{id}")
    ResponseEntity<ScheduleDAO> getScheduleById(@PathVariable int id);

    @PostMapping
    ResponseEntity<ScheduleDAO> createSchedule(@RequestBody ScheduleDAO scheduleDAO);

    @PutMapping("/{id}")
    ResponseEntity<ScheduleDAO> updateSchedule(@PathVariable int id, @RequestBody ScheduleDAO scheduleDAO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteSchedule(@PathVariable int id);
}
