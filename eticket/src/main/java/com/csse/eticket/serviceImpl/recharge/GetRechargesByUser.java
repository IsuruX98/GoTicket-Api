package com.csse.eticket.serviceImpl.recharge;

import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.Recharge;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.RechargeRepository;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.RechargeCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetRechargesByUser implements RechargeCommand {
    private final RechargeRepository rechargeRepository;

    private final UserRepository userRepository;

    private final JwtFilter jwtFilter;

    public GetRechargesByUser(RechargeRepository rechargeRepository, UserRepository userRepository, JwtFilter jwtFilter) {
        this.rechargeRepository = rechargeRepository;
        this.userRepository = userRepository;
        this.jwtFilter = jwtFilter;
    }

    @Override
    public ResponseEntity<?> execute() {
        String username = jwtFilter.getCurrentUser();

        User user = userRepository.findByEmailId(username);

        List<Recharge> recharges = rechargeRepository.findRechargesByUserId(user.getId());

        return ResponseEntity.ok().body(recharges);
    }
}
