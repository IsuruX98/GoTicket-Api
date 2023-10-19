package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.model.Bus;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetTopUpAccsCommand implements TopUpAccCommand {
    private final TopUpAccRepository topUpAccRepository;

    public GetTopUpAccsCommand(TopUpAccRepository topUpAccRepository) {
        this.topUpAccRepository = topUpAccRepository;
    }

    @Override
    public ResponseEntity<?> execute() {
        List<TopUpAcc> topUpAccList = topUpAccRepository.findAll();

        return ResponseEntity.ok().body(topUpAccList);
    }
}
