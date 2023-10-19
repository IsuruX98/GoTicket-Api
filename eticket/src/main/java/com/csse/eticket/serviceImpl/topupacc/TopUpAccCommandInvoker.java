package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopUpAccCommandInvoker {
    public ResponseEntity<?> executeCommand(TopUpAccCommand command) {
        return command.execute();
    }
}
