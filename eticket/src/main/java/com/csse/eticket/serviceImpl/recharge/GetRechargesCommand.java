package com.csse.eticket.serviceImpl.recharge;

import com.csse.eticket.model.Recharge;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.RechargeRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.service.RechargeCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetRechargesCommand implements RechargeCommand {
    private final RechargeRepository rechargeRepository;

    public GetRechargesCommand(RechargeRepository rechargeRepository) {
        this.rechargeRepository = rechargeRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        List<Recharge> rechargeList = rechargeRepository.findAll();

        return ResponseEntity.ok().body(rechargeList);
    }
}
