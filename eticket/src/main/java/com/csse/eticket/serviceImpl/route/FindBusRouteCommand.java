package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.http.ResponseEntity;

public class FindBusRouteCommand implements BusRouteCommand {
    private final BusRouteRepository busRouteRepository;

    private final BusRouteDao busRouteDao;

    public FindBusRouteCommand(BusRouteRepository busRouteRepository, BusRouteDao busRouteDao) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteDao = busRouteDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        BusRoute busRoute = busRouteRepository.findByRouteName(busRouteDao.getRouteName());

        return ResponseEntity.ok().body(busRoute);
    }
}
