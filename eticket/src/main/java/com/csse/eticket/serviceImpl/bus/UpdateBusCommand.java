package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusRoute;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.BusRouteRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UpdateBusCommand implements BusCommand {
    private final BusRepository busRepository;

    private final UserRepository userRepository;

    private final BusRouteRepository routeRepository;

    private final BusDao busDao;

    @Autowired
    public UpdateBusCommand(BusRepository busRepository, UserRepository userRepository, BusRouteRepository routeRepository, BusDao busDao) {
        this.busRepository = busRepository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.busDao = busDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Bus bus = busRepository.findByBusNo(busDao.getBusNo());

        if(bus != null){
            Bus updatedBus = new Bus();
            updatedBus.setBusNo(busDao.getBusNo());
            User user = userRepository.findByEmailId(busDao.getUserEmail());
            BusRoute route = routeRepository.findByRouteName(busDao.getRouteName());
            updatedBus.setRoute(route);
            updatedBus.setUser(user);
            updatedBus.setIncome(0);

            return ResponseEntity.ok().body(busRepository.save(updatedBus));
        }
        return ResponseEntity.badRequest().body("Bus not found");
    }
}
