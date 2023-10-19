package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddBusRouteCommand implements BusRouteCommand {
    private final BusRouteRepository busRouteRepository;
    private final BusRouteDao busRouteDao;

    public AddBusRouteCommand(BusRouteRepository busRouteRepository, BusRouteDao busRouteDao) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteDao = busRouteDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        BusRoute busRoute = new BusRoute();
        busRoute.setRouteName(busRouteDao.getRouteName());
        busRoute.setStartPoint(busRouteDao.getStartPoint());
        busRoute.setEndPoint(busRouteDao.getEndPoint());
        busRoute.setTicketPrice(busRouteDao.getTicketPrice());
        return ResponseEntity.ok().body(busRouteRepository.save(busRoute));
    }
}
