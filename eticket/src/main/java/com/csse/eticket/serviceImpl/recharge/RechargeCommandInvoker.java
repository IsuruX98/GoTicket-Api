package com.csse.eticket.serviceImpl.recharge;

import com.csse.eticket.service.RechargeCommand;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RechargeCommandInvoker {
    public ResponseEntity<?> executeCommand(RechargeCommand command) {
        return command.execute();
    }
}
