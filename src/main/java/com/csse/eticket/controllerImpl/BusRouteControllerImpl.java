package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.BusRouteController;
import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusRouteCommand;
import com.csse.eticket.serviceImpl.route.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusRouteControllerImpl implements BusRouteController {
    private final BusRouteRepository busRouteRepository;

    private final BusRouteCommandInvoker busRouteCommandInvoker;

    public BusRouteControllerImpl(BusRouteRepository busRouteRepository, BusRouteCommandInvoker busRouteCommandInvoker) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteCommandInvoker = busRouteCommandInvoker;
    }

    @Override
    public ResponseEntity<?> addBusRoute(BusRouteDao busRouteDao) {
        BusRouteCommand addCommand = new AddBusRouteCommand(busRouteRepository, busRouteDao);
        return ResponseEntity.ok().body(busRouteCommandInvoker.executeCommand(addCommand));
    }

    @Override
    public ResponseEntity<?> updateBusRoute(BusRouteDao busRouteDao) {
        BusRouteCommand updateCommand = new UpdateBusRouteCommand(busRouteRepository, busRouteDao);
        return ResponseEntity.ok().body(busRouteCommandInvoker.executeCommand(updateCommand));
    }

    @Override
    public ResponseEntity<?> deleteBusRoute(BusRouteDao busRouteDao) {
        BusRouteCommand deleteCommand = new DeleteBusRouteCommand(busRouteRepository, busRouteDao);
        return ResponseEntity.ok().body(busRouteCommandInvoker.executeCommand(deleteCommand));
    }

    @Override
    public ResponseEntity<?> getAllBusRoutes() {
        BusRouteCommand getBuseRoutesCommand = new GetBusRoutesCommand(busRouteRepository);
        return ResponseEntity.ok().body(busRouteCommandInvoker.executeCommand(getBuseRoutesCommand));
    }

    @Override
    public ResponseEntity<?> findBusRoute(BusRouteDao busRouteDao) {
        BusRouteCommand findBusRouteCommand = new FindBusRouteCommand(busRouteRepository, busRouteDao);
        return ResponseEntity.ok().body(busRouteCommandInvoker.executeCommand(findBusRouteCommand));
    }
}
