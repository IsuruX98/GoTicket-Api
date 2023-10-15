package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.ScheduleController;
import com.csse.eticket.dao.ScheduleDAO;
import com.csse.eticket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ScheduleControllerImpl implements ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleControllerImpl(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    public ResponseEntity<List<ScheduleDAO>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @Override
    public ResponseEntity<ScheduleDAO> getScheduleById(int id) {
        ScheduleDAO schedule = scheduleService.getScheduleById(id);
        return schedule != null ? ResponseEntity.ok(schedule) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<ScheduleDAO> createSchedule(ScheduleDAO scheduleDAO) {
        ScheduleDAO createdSchedule = scheduleService.createSchedule(scheduleDAO);
        return ResponseEntity.ok(createdSchedule);
    }

    @Override
    public ResponseEntity<ScheduleDAO> updateSchedule(int id, ScheduleDAO scheduleDAO) {
        ScheduleDAO updatedSchedule = scheduleService.updateSchedule(id, scheduleDAO);
        return updatedSchedule != null ? ResponseEntity.ok(updatedSchedule) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> deleteSchedule(int id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
