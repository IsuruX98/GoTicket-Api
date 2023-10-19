package com.csse.eticket.serviceImpl.schedule;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.dao.BusSheduleDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.model.BusSchedule;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.BusScheduleRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusScheduleCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class AddBusScheduleCommand implements BusScheduleCommand {
    private final BusRepository busRepository;

    private final BusScheduleRepository busScheduleRepository;

    private final BusRouteRepository routeRepository;

    private final BusSheduleDao busSheduleDao;

    public AddBusScheduleCommand(BusRepository busRepository, BusScheduleRepository busScheduleRepository, BusRouteRepository routeRepository, BusSheduleDao busSheduleDao) {
        this.busRepository = busRepository;
        this.busScheduleRepository = busScheduleRepository;
        this.busSheduleDao = busSheduleDao;
        this.routeRepository = routeRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        BusSchedule busSchedule = new BusSchedule();
        busSchedule.setDate(LocalDate.parse(busSheduleDao.getDate()));
        busSchedule.setStartTime(LocalTime.parse(busSheduleDao.getStartTime()));
        busSchedule.setEndTime(LocalTime.parse(busSheduleDao.getEndTime()));
        BusRoute route = routeRepository.findByRouteName(busSheduleDao.getRouteName());
        busSchedule.setRouteName(route);
        Bus bus = busRepository.findByBusNo(busSheduleDao.getBusNo());
        busSchedule.setBus(bus);
        return ResponseEntity.ok().body(busScheduleRepository.save(busSchedule));
    }
}
