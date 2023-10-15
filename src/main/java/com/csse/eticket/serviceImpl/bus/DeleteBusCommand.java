package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.service.BusCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteBusCommand implements BusCommand {
    private final BusRepository busRepository;

    private final BusDao busDao;

    public DeleteBusCommand(BusRepository busRepository, BusDao busDao) {
        this.busRepository = busRepository;
        this.busDao = busDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        Bus bus = busRepository.findByBusNo(busDao.getBusNo());

        if(bus != null){
            busRepository.deleteById(busDao.getBusNo());
            return ResponseEntity.ok().body("Bus Deleted");
        }

        return ResponseEntity.badRequest().body("Bus not found");
    }
}
