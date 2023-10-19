package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AddBusCommand implements BusCommand {
    private final BusRepository busRepository;

    private final UserRepository userRepository;

    private final BusRouteRepository routeRepository;

    private final BusDao busDao;

    public AddBusCommand(BusRepository busRepository, UserRepository userRepository, BusRouteRepository routeRepository, BusDao busDao) {
        this.busRepository = busRepository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.busDao = busDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Bus bus = new Bus();
        bus.setBusNo(busDao.getBusNo());
        User user = userRepository.findByEmailId(busDao.getUserEmail());
        BusRoute route = routeRepository.findByRouteName(busDao.getRouteName());
        bus.setRoute(route);
        bus.setUser(user);
        bus.setIncome(0);
        return ResponseEntity.ok().body(busRepository.save(bus));
    }
}
