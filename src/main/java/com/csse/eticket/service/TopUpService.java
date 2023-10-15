package com.csse.eticket.service;

import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.dao.TopUpDao;
import org.springframework.http.ResponseEntity;

public interface TopUpService {
    ResponseEntity<?> createTopUpAcc(TopUpAccDao topUpAccDao);

    ResponseEntity<?> getTopUpAccountsByUser();

    ResponseEntity<?> transactMoney(TopUpDao topUpDao);
}
