package com.csse.eticket.controllerImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.controller.TopUpController;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.dao.TopUpDao;
import com.csse.eticket.service.TopUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class TopUpControllerImpl implements TopUpController {
    @Autowired
    private final TopUpService topUpService;

    public TopUpControllerImpl(TopUpService topUpService) {
        this.topUpService = topUpService;
    }

    @Override
    public ResponseEntity<?> createTopUpAcc(TopUpAccDao topUpAccDao) {
        try {
            return topUpService.createTopUpAcc(topUpAccDao);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> getTopUpAccountsByUser() {
        try {
            return topUpService.getTopUpAccountsByUser();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> transactMoney(TopUpDao topUpDao) {
        try {
            return topUpService.transactMoney(topUpDao);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }
}
