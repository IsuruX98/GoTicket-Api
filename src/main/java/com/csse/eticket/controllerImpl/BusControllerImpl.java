package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.BusController;
import com.csse.eticket.dao.BusDao;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.serviceImpl.bus.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusControllerImpl implements BusController {
    private final BusRepository busRepository;

    private final BusCommandInvoker busCommandInvoker;

    private final UserRepository userRepository;

    private final BusRouteRepository busRouteRepository;

    @Autowired
    public BusControllerImpl(BusRepository busRepository, BusCommandInvoker busCommandInvoker, UserRepository userRepository, BusRouteRepository busRouteRepository) {
        this.busRepository = busRepository;
        this.busCommandInvoker = busCommandInvoker;
        this.userRepository = userRepository;
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public ResponseEntity<?> addBus(BusDao busDao) {
        BusCommand addCommand = new AddBusCommand(busRepository, userRepository, busRouteRepository, busDao);
        return ResponseEntity.ok().body(busCommandInvoker.executeCommand(addCommand));
    }

    @Override
    public ResponseEntity<?> updateBus(BusDao busDao) {
        BusCommand updateCommand = new UpdateBusCommand(busRepository, userRepository, busRouteRepository, busDao);
        return ResponseEntity.ok().body(busCommandInvoker.executeCommand(updateCommand));
    }

    @Override
    public ResponseEntity<?> deleteBus(BusDao busDao) {
        BusCommand deleteCommand = new DeleteBusCommand(busRepository, busDao);
        return ResponseEntity.ok().body(busCommandInvoker.executeCommand(deleteCommand));
    }

    @Override
    public ResponseEntity<?> getAllBuses() {
        BusCommand getBusesCommand = new GetBusesCommand(busRepository);
        return ResponseEntity.ok().body(busCommandInvoker.executeCommand(getBusesCommand));
    }

    @Override
    public ResponseEntity<?> findBus(BusDao busDao) {
        BusCommand findBusCommand = new FindBusCommand(busRepository, busDao);
        return ResponseEntity.ok().body(busCommandInvoker.executeCommand(findBusCommand));
    }
}
