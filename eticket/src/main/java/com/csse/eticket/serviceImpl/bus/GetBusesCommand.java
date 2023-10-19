package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.service.BusCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetBusesCommand implements BusCommand {
    private final BusRepository busRepository;

    public GetBusesCommand(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        List<Bus> buses = busRepository.findAll();

        return ResponseEntity.badRequest().body(buses);
    }
}
