package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;

public class FindTopUpAccCommand implements TopUpAccCommand {
    private final TopUpAccRepository topUpAccRepository;

    private final TopUpAccDao topUpAccDao;

    public FindTopUpAccCommand(TopUpAccRepository topUpAccRepository, TopUpAccDao topUpAccDao) {
        this.topUpAccRepository = topUpAccRepository;
        this.topUpAccDao = topUpAccDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        TopUpAcc topUpAcc = topUpAccRepository.findByAccId(topUpAccDao.getId());

        return ResponseEntity.ok().body(topUpAcc);
    }
}
