package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.BusScheduleController;
import com.csse.eticket.dao.BusSheduleDao;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.BusScheduleRepository;
import com.csse.eticket.service.BusScheduleCommand;
import com.csse.eticket.serviceImpl.schedule.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusScheduleControllerImpl implements BusScheduleController {
    private final BusRepository busRepository;

    private final BusRouteRepository busRouteRepository;

    private final BusScheduleRepository busScheduleRepository;

    private final BusScheduleCommandInvoker busScheduleCommandInvoker;

    public BusScheduleControllerImpl(BusRepository busRepository, BusRouteRepository busRouteRepository, BusScheduleRepository busScheduleRepository, BusScheduleCommandInvoker busScheduleCommandInvoker) {
        this.busRepository = busRepository;
        this.busRouteRepository = busRouteRepository;
        this.busScheduleRepository = busScheduleRepository;
        this.busScheduleCommandInvoker = busScheduleCommandInvoker;
    }

    @Override
    public ResponseEntity<?> addSchedule(BusSheduleDao busSheduleDao) {
        BusScheduleCommand addCommand = new AddBusScheduleCommand(busRepository, busScheduleRepository, busRouteRepository, busSheduleDao);
        return ResponseEntity.ok().body(busScheduleCommandInvoker.executeCommand(addCommand));
    }

    @Override
    public ResponseEntity<?> updateSchedule(BusSheduleDao busSheduleDao) {
        BusScheduleCommand updateCommand = new UpdateBusScheduleCommand(busRepository, busScheduleRepository, busRouteRepository, busSheduleDao);
        return ResponseEntity.ok().body(busScheduleCommandInvoker.executeCommand(updateCommand));
    }

    @Override
    public ResponseEntity<?> deleteSchedule(BusSheduleDao busSheduleDao) {
        BusScheduleCommand deleteCommand = new DeleteBusScheduleCommand(busScheduleRepository, busSheduleDao);
        return ResponseEntity.ok().body(busScheduleCommandInvoker.executeCommand(deleteCommand));
    }

    @Override
    public ResponseEntity<?> getAllSchedule() {
        BusScheduleCommand getBusScheduleCommand = new GetBusSchedulesCommand(busScheduleRepository);
        return ResponseEntity.ok().body(busScheduleCommandInvoker.executeCommand(getBusScheduleCommand));
    }

    @Override
    public ResponseEntity<?> findSchedule(BusSheduleDao busSheduleDao) {
        BusScheduleCommand findBusCommand = new FindBusScheduleCommand(busScheduleRepository, busSheduleDao);
        return ResponseEntity.ok().body(busScheduleCommandInvoker.executeCommand(findBusCommand));
    }
}
