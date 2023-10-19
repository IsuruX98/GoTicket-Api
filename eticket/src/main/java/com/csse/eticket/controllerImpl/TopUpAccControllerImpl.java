package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.TopUpAccController;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.TopUpAccCommand;
import com.csse.eticket.serviceImpl.TopUpFactory;
import com.csse.eticket.serviceImpl.bus.DeleteBusCommand;
import com.csse.eticket.serviceImpl.bus.FindBusCommand;
import com.csse.eticket.serviceImpl.bus.GetBusesCommand;
import com.csse.eticket.serviceImpl.topupacc.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopUpAccControllerImpl implements TopUpAccController {
    private final UserRepository userRepository;

    private final TopUpAccRepository topUpAccRepository;

    private final TopUpFactory topUpFactory;

    private final JwtFilter jwtFilter;

    private final TopUpAccCommandInvoker topUpAccCommandInvoker;

    public TopUpAccControllerImpl(UserRepository userRepository, TopUpAccRepository topUpAccRepository, TopUpAccDao topUpAccDao, TopUpFactory topUpFactory, JwtFilter jwtFilter, TopUpAccCommandInvoker topUpAccCommandInvoker) {
        this.userRepository = userRepository;
        this.topUpAccRepository = topUpAccRepository;
        this.topUpFactory = topUpFactory;
        this.jwtFilter = jwtFilter;
        this.topUpAccCommandInvoker = topUpAccCommandInvoker;
    }

    @Override
    public ResponseEntity<?> addTopUpAcc(TopUpAccDao topUpAccDao) {
        TopUpAccCommand addCommand = new AddTopUpAccCommand(userRepository, topUpAccRepository, topUpAccDao, topUpFactory, jwtFilter);
        return ResponseEntity.ok().body(topUpAccCommandInvoker.executeCommand(addCommand));
    }

    @Override
    public ResponseEntity<?> deleteTopUpAcc(TopUpAccDao topUpAccDao) {
        TopUpAccCommand deleteCommand = new DeleteTopUpAccCommand(topUpAccRepository, topUpAccDao);
        return ResponseEntity.ok().body(topUpAccCommandInvoker.executeCommand(deleteCommand));
    }

    @Override
    public ResponseEntity<?> getAllTopUpAcca() {
        TopUpAccCommand getTopUpAccCommand = new GetTopUpAccsCommand(topUpAccRepository);
        return ResponseEntity.ok().body(topUpAccCommandInvoker.executeCommand(getTopUpAccCommand));
    }

    @Override
    public ResponseEntity<?> findTopUpAcc(TopUpAccDao topUpAccDao) {
        TopUpAccCommand findTopUpAccCommand = new FindTopUpAccCommand(topUpAccRepository, topUpAccDao);
        return ResponseEntity.ok().body(topUpAccCommandInvoker.executeCommand(findTopUpAccCommand));
    }

    @Override
    public ResponseEntity<?> getTopUpAccountsByUser() {
        TopUpAccCommand findTopUpAccByUser = new GetTopUpAccByUser(topUpAccRepository, userRepository, jwtFilter);
        return ResponseEntity.ok().body(topUpAccCommandInvoker.executeCommand(findTopUpAccByUser));
    }
}
