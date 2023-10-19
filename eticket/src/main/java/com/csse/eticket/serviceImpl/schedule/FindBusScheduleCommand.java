package com.csse.eticket.serviceImpl.schedule;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.dao.BusSheduleDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusSchedule;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusScheduleRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusScheduleCommand;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class FindBusScheduleCommand implements BusScheduleCommand {
    private final BusScheduleRepository busScheduleRepository;

    private final BusSheduleDao busSheduleDao;

    public FindBusScheduleCommand(BusScheduleRepository busScheduleRepository, BusSheduleDao busSheduleDao) {
        this.busScheduleRepository = busScheduleRepository;
        this.busSheduleDao = busSheduleDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Optional<BusSchedule> busSchedule = busScheduleRepository.findByScheduleId(busSheduleDao.getScheduleId());

        return ResponseEntity.ok().body(busSchedule);
    }
}
