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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Service
@Transactional
public class UpdateBusScheduleCommand implements BusScheduleCommand {
    private final BusRepository busRepository;

    private final BusScheduleRepository busScheduleRepository;

    private final BusRouteRepository routeRepository;

    private final BusSheduleDao busSheduleDao;

    @Autowired
    public UpdateBusScheduleCommand(BusRepository busRepository, BusScheduleRepository busScheduleRepository, BusRouteRepository routeRepository, BusSheduleDao busSheduleDao) {
        this.busRepository = busRepository;
        this.busScheduleRepository = busScheduleRepository;
        this.routeRepository = routeRepository;
        this.busSheduleDao = busSheduleDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<BusSchedule> busSchedule = busScheduleRepository.findByScheduleId(busSheduleDao.getScheduleId());

        if(busSchedule != null){
            BusSchedule updatedBusSchedule = new BusSchedule();
            updatedBusSchedule.setScheduleId(busSheduleDao.getScheduleId());
            updatedBusSchedule.setDate(LocalDate.parse(busSheduleDao.getDate()));
            updatedBusSchedule.setStartTime(LocalTime.parse(busSheduleDao.getStartTime()));
            updatedBusSchedule.setEndTime(LocalTime.parse(busSheduleDao.getEndTime()));

            BusRoute route = routeRepository.findByRouteName(busSheduleDao.getRouteName());
            updatedBusSchedule.setRouteName(route);

            Bus bus = busRepository.findByBusNo(busSheduleDao.getBusNo());
            updatedBusSchedule.setBus(bus);

            return ResponseEntity.ok().body(busScheduleRepository.save(updatedBusSchedule));
        }
        return ResponseEntity.badRequest().body("Bus Schedule not found");
    }
}
