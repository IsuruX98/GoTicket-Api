package com.csse.eticket.controllerImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.controller.RechargeController;
import com.csse.eticket.dao.RechargeDao;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.repository.RechargeRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.RechargeCommand;
import com.csse.eticket.service.TopUpAccCommand;
import com.csse.eticket.serviceImpl.recharge.AddRechargeCommand;
import com.csse.eticket.serviceImpl.recharge.GetRechargesByUser;
import com.csse.eticket.serviceImpl.recharge.GetRechargesCommand;
import com.csse.eticket.serviceImpl.recharge.RechargeCommandInvoker;
import com.csse.eticket.serviceImpl.topupacc.AddTopUpAccCommand;
import com.csse.eticket.serviceImpl.topupacc.GetTopUpAccByUser;
import com.csse.eticket.serviceImpl.topupacc.GetTopUpAccsCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RechargeControllerImpl implements RechargeController {
    private final UserRepository userRepository;

    private final TopUpAccRepository topUpAccRepository;

    private final RechargeRepository rechargeRepository;

    private final JwtFilter jwtFilter;

    private final RechargeCommandInvoker rechargeCommandInvoker;

    public RechargeControllerImpl(UserRepository userRepository, TopUpAccRepository topUpAccRepository, RechargeRepository rechargeRepository, JwtFilter jwtFilter, RechargeCommandInvoker rechargeCommandInvoker) {
        this.userRepository = userRepository;
        this.topUpAccRepository = topUpAccRepository;
        this.rechargeRepository = rechargeRepository;
        this.jwtFilter = jwtFilter;
        this.rechargeCommandInvoker = rechargeCommandInvoker;
    }

    @Override
    public ResponseEntity<?> recharge(RechargeDao rechargeDao) {
        RechargeCommand addCommand = new AddRechargeCommand(topUpAccRepository, rechargeRepository, rechargeDao);
        return ResponseEntity.ok().body(rechargeCommandInvoker.executeCommand(addCommand));
    }

    @Override
    public ResponseEntity<?> getAllRecharges() {
        RechargeCommand getRechargesCommand = new GetRechargesCommand(rechargeRepository);
        return ResponseEntity.ok().body(rechargeCommandInvoker.executeCommand(getRechargesCommand));
    }

    @Override
    public ResponseEntity<?> getRechargesByUser() {
        RechargeCommand findRechargesByUser = new GetRechargesByUser(rechargeRepository, userRepository, jwtFilter);
        return ResponseEntity.ok().body(rechargeCommandInvoker.executeCommand(findRechargesByUser));
    }
}
