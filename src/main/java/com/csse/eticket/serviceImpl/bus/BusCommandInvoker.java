package com.csse.eticket.serviceImpl.bus;

import com.csse.eticket.service.BusCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusCommandInvoker {
    public ResponseEntity<?> executeCommand(BusCommand command) {
        return command.execute();
    }
}
