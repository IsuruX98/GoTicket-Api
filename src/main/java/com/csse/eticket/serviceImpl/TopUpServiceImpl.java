package com.csse.eticket.serviceImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.dao.TopUpDao;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.Transactions;
import com.csse.eticket.model.topup.FamilyTopUpAcc;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.*;
import com.csse.eticket.repository.TransactionsRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.TopUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class TopUpServiceImpl implements TopUpService {
    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopUpAccRepository topUpAccRepository;

    @Autowired
    private final TopUpFactory topUpFactory;

    @Autowired
    private final TransactionsRepository transactionsRepository;

    public TopUpServiceImpl(TopUpFactory topUpFactory, TransactionsRepository transactionsRepository) {
        this.topUpFactory = topUpFactory;
        this.transactionsRepository = transactionsRepository;
    }

    @Override
    public ResponseEntity<?> createTopUpAcc(TopUpAccDao topUpAccDao) {
        try {
            TopUpAcc topUpAcc = topUpFactory.createTopUp(topUpAccDao.getType());
            String username = jwtFilter.getCurrentUser();
            User currentUser = userRepository.findByEmailId(username);

            topUpAcc.setUser(currentUser);
            topUpAcc.setType(topUpAccDao.getType());

            if (topUpAcc instanceof FamilyTopUpAcc) {
                ((FamilyTopUpAcc) topUpAcc).setChildName(topUpAccDao.getFamilyMembers());
                topUpAcc.setBalance(2000);
            } else {
                topUpAcc.setBalance(500);
            }

            return ResponseEntity.ok().body(topUpAccRepository.save(topUpAcc));
        }catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    public ResponseEntity<?> getTopUpAccountsByUser() {
        try {
            String username = jwtFilter.getCurrentUser();
            User currentUser = userRepository.findByEmailId(username);

            List<TopUpAcc> topUpAccList = topUpAccRepository.findAllByUser(currentUser);

            return ResponseEntity.ok().body(topUpAccList);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> transactMoney(TopUpDao topUpDao) {
        try {
            float newBalance;
            Integer accId = topUpDao.getId();
            TopUpAcc topUpAcc = topUpAccRepository.findByAccId(accId);
            log.info(String.valueOf(topUpAcc.getId()));
            log.info(String.valueOf(topUpDao.getAmount()));
            float availableBalance = topUpAcc.getBalance();

            if(topUpDao.getType().equalsIgnoreCase("debit")){
                newBalance = availableBalance + topUpDao.getAmount();
            }else{
                if(availableBalance > topUpDao.getAmount()){
                    newBalance = availableBalance - topUpDao.getAmount();
                }else{
                    return ResponseEntity.badRequest().body("Insufficient Balance");
                }
            }

            Transactions transaction = new Transactions();
            transaction.setTopUpAcc(topUpAcc);
            transaction.setType(topUpDao.getType());
            Date transactionDate = new Date();

            Instant instant = transactionDate.toInstant();
            LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
            transaction.setDate(localDate);

            transaction.setAmount(topUpDao.getAmount());

            transactionsRepository.save(transaction);

            topUpAcc.setBalance(newBalance);

            return ResponseEntity.ok().body(topUpAcc);
        }catch (Exception ex){
            log.error(ex.getMessage());
            return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
        }
    }
}
