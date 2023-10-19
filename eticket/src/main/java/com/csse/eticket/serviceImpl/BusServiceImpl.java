package com.csse.eticket.serviceImpl;

import com.csse.eticket.model.Bus;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.service.BusService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BusServiceImpl implements BusService {

    @Autowired
    BusRepository busRepository;

    @Override
    public Bus addAmount(String busNo, float amount) {
        Bus bus = busRepository.findByBusNo(busNo);
        bus.setIncome(bus.getIncome() + amount);
        busRepository.save(bus);
        return bus;
    }
}
