package com.csse.eticket.serviceImpl.route;

import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.BusRouteCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusRouteCommandInvoker {
    public ResponseEntity<?> executeCommand(BusRouteCommand command) {
        return command.execute();
    }
}
