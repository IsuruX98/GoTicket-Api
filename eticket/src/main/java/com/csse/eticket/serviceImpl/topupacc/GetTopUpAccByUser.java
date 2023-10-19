package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.TopUpAccCommand;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetTopUpAccByUser implements TopUpAccCommand {
    private final TopUpAccRepository topUpAccRepository;

    private final UserRepository userRepository;

    private final JwtFilter jwtFilter;

    public GetTopUpAccByUser(TopUpAccRepository topUpAccRepository, UserRepository userRepository, JwtFilter jwtFilter) {
        this.topUpAccRepository = topUpAccRepository;
        this.userRepository = userRepository;
        this.jwtFilter = jwtFilter;
    }

    @Override
    public ResponseEntity<?> execute() {
        String username = jwtFilter.getCurrentUser();

        User user = userRepository.findByEmailId(username);

        List<TopUpAcc> topUpAcc = topUpAccRepository.findAccByUserId(user.getId());

        return ResponseEntity.ok().body(topUpAcc);
    }
}
