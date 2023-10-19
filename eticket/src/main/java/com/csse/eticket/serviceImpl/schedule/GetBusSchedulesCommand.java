package com.csse.eticket.serviceImpl.schedule;

import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusSchedule;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusScheduleRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusScheduleCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetBusSchedulesCommand implements BusScheduleCommand {
    private final BusScheduleRepository busScheduleRepository;

    public GetBusSchedulesCommand(BusScheduleRepository busScheduleRepository) {
        this.busScheduleRepository = busScheduleRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        List<BusSchedule> busSchedules = busScheduleRepository.findAll();

        return ResponseEntity.badRequest().body(busSchedules);
    }
}
