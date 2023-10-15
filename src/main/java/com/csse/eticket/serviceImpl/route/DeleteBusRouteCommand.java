package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteBusRouteCommand implements BusRouteCommand {
    private final BusRouteRepository busRouteRepository;

    private final BusRouteDao busRouteDao;

    public DeleteBusRouteCommand(BusRouteRepository busRouteRepository, BusRouteDao busRouteDao) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteDao = busRouteDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        BusRoute busRoute = busRouteRepository.findByRouteName(busRouteDao.getRouteName());

        if(busRoute != null){
            busRouteRepository.deleteById(busRouteDao.getRouteName());
            return ResponseEntity.ok().body("Bus Route Deleted");
        }

        return ResponseEntity.badRequest().body("Bus Route not found");
    }
}
