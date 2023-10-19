package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.dao.BusRouteDao;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateBusRouteCommand implements BusRouteCommand {
    private final BusRouteRepository busRouteRepository;

    private final BusRouteDao busRouteDao;

    @Autowired
    public UpdateBusRouteCommand(BusRouteRepository busRouteRepository, BusRouteDao busRouteDao) {
        this.busRouteRepository = busRouteRepository;
        this.busRouteDao = busRouteDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        BusRoute busRoute = busRouteRepository.findByRouteName(busRouteDao.getRouteName());

        if(busRoute != null){
            BusRoute updatedBusRoute = new BusRoute();
            updatedBusRoute.setRouteName(busRouteDao.getRouteName());
            updatedBusRoute.setStartPoint(busRouteDao.getStartPoint());
            updatedBusRoute.setEndPoint(busRouteDao.getEndPoint());
            updatedBusRoute.setTicketPrice(busRouteDao.getTicketPrice());

            return ResponseEntity.ok().body(busRouteRepository.save(updatedBusRoute));
        }
        return ResponseEntity.badRequest().body("Bus Route not found");
    }
}
