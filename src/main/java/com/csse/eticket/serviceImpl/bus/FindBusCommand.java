package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.service.BusCommand;
import org.springframework.http.ResponseEntity;

public class FindBusCommand implements BusCommand {
    private final BusRepository busRepository;

    private final BusDao busDao;

    public FindBusCommand(BusRepository busRepository, BusDao busDao) {
        this.busRepository = busRepository;
        this.busDao = busDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Bus bus = busRepository.findByBusNo(busDao.getBusNo());

        return ResponseEntity.ok().body(bus);
    }
}
