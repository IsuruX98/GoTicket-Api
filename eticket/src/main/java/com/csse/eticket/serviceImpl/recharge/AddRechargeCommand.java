package com.csse.eticket.serviceImpl.recharge;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.dao.RechargeDao;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.model.Recharge;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.repository.RechargeRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.service.RechargeCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class AddRechargeCommand implements RechargeCommand {
    private final TopUpAccRepository topUpAccRepository;

    private final RechargeRepository rechargeRepository;

    private final RechargeDao rechargeDao;


    public AddRechargeCommand(TopUpAccRepository topUpAccRepository, RechargeRepository rechargeRepository, RechargeDao rechargeDao) {
        this.topUpAccRepository = topUpAccRepository;
        this.rechargeRepository = rechargeRepository;
        this.rechargeDao = rechargeDao;
    }

    @Override
    @Transactional
    public ResponseEntity<?> execute() {
        try {
            float newBalance;
            Integer accId = rechargeDao.getId();
            log.info(String.valueOf(rechargeDao.getId()));
            TopUpAcc topUpAcc = topUpAccRepository.findByAccId(accId);
            log.info(String.valueOf(topUpAcc.getId()));
            log.info(String.valueOf(rechargeDao.getAmount()));
            float availableBalance = topUpAcc.getBalance();

            Recharge recharge = new Recharge();
            recharge.setTopUpAcc(topUpAcc);
            Date transactionDate = new Date();

            Instant instant = transactionDate.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            recharge.setDate(localDate);

            LocalTime currentTime = LocalTime.now();
            recharge.setTime(currentTime);

            recharge.setAmount(rechargeDao.getAmount());

            rechargeRepository.save(recharge);

            newBalance = availableBalance + rechargeDao.getAmount();
            topUpAcc.setBalance(newBalance);

            return ResponseEntity.ok().body(topUpAcc);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
        }
    }
}
