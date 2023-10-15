package com.csse.eticket.serviceImpl;

import com.csse.eticket.dao.ScheduleDAO;
import com.csse.eticket.model.Schedule;
import com.csse.eticket.repository.ScheduleRepository;
import com.csse.eticket.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<ScheduleDAO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(this::convertToDAO)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDAO getScheduleById(int id) {
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);
        return optionalSchedule.map(this::convertToDAO).orElse(null);
    }

    @Override
    public ScheduleDAO createSchedule(ScheduleDAO scheduleDAO) {
        Schedule schedule = convertToEntity(scheduleDAO);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return convertToDAO(savedSchedule);
    }

    @Override
    public ScheduleDAO updateSchedule(int id, ScheduleDAO scheduleDAO) {
        if (scheduleRepository.existsById(id)) {
            Schedule schedule = convertToEntity(scheduleDAO);
            schedule.setScheduleId(id);
            Schedule updatedSchedule = scheduleRepository.save(schedule);
            return convertToDAO(updatedSchedule);
        }
        return null;
    }

    @Override
    public void deleteSchedule(int id) {
        scheduleRepository.deleteById(id);
    }

    private ScheduleDAO convertToDAO(Schedule schedule) {
        ScheduleDAO scheduleDAO = new ScheduleDAO();
        scheduleDAO.setScheduleId(schedule.getScheduleId());
        scheduleDAO.setStartTime(schedule.getStartTime());
        scheduleDAO.setEndTime(schedule.getEndTime());
        scheduleDAO.setDate(schedule.getDate());
        scheduleDAO.setBusId(schedule.getBusId());
        scheduleDAO.setRouteName(schedule.getRouteName());
        return scheduleDAO;
    }

    private Schedule convertToEntity(ScheduleDAO scheduleDAO) {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleDAO.getScheduleId());
        schedule.setStartTime(scheduleDAO.getStartTime());
        schedule.setEndTime(scheduleDAO.getEndTime());
        schedule.setDate(scheduleDAO.getDate());
        schedule.setBusId(scheduleDAO.getBusId());
        schedule.setRouteName(scheduleDAO.getRouteName());
        return schedule;
    }
}
