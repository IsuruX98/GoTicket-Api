package com.csse.eticket.serviceImpl.schedule;

import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusScheduleCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusScheduleCommandInvoker {
    public ResponseEntity<?> executeCommand(BusScheduleCommand command) {
        return command.execute();
    }
}
