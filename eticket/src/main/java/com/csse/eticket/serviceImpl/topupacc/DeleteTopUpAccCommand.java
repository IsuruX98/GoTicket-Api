package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.dao.BusDao;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.model.Bus;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.BusRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeleteTopUpAccCommand implements TopUpAccCommand {
    private final TopUpAccRepository topUpAccRepository;

    private final TopUpAccDao topUpAccDao;

    public DeleteTopUpAccCommand(TopUpAccRepository topUpAccRepository, TopUpAccDao topUpAccDao) {
        this.topUpAccRepository = topUpAccRepository;
        this.topUpAccDao = topUpAccDao;
    }

    @Override
    public ResponseEntity<?> execute() {
        TopUpAcc topUpAcc = topUpAccRepository.findByAccId(topUpAccDao.getId());

        if(topUpAcc != null){
            topUpAccRepository.deleteById(topUpAccDao.getId());
            return ResponseEntity.ok().body("TopUp Account Deleted");
        }

        return ResponseEntity.badRequest().body("Top up Account not found");
    }
}
