package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetBusRoutesCommand implements BusRouteCommand {
    private final BusRouteRepository busRouteRepository;

    public GetBusRoutesCommand(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        List<BusRoute> busRoutes = busRouteRepository.findAll();

        return ResponseEntity.ok().body(busRoutes);
    }
}
