package com.csse.eticket.service;

import com.csse.eticket.dao.ScheduleDAO;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDAO> getAllSchedules();
    ScheduleDAO getScheduleById(int id);
    ScheduleDAO createSchedule(ScheduleDAO scheduleDAO);
    ScheduleDAO updateSchedule(int id, ScheduleDAO scheduleDAO);
    void deleteSchedule(int id);
}
