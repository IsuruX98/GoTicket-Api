package com.csse.eticket.serviceImpl.topupacc;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.dao.TopUpAccDao;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.model.topup.FamilyTopUpAcc;
import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import com.csse.eticket.repository.topups.TopUpAccRepository;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.BusCommand;
import com.csse.eticket.service.TopUpAccCommand;
import com.csse.eticket.serviceImpl.TopUpFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddTopUpAccCommand implements TopUpAccCommand {
    private final UserRepository userRepository;

    private final TopUpAccRepository topUpAccRepository;

    private final TopUpAccDao topUpAccDao;

    private final TopUpFactory topUpFactory;

    private final JwtFilter jwtFilter;

    public AddTopUpAccCommand(UserRepository userRepository, TopUpAccRepository topUpAccRepository, TopUpAccDao topUpAccDao, TopUpFactory topUpFactory, JwtFilter jwtFilter) {
        this.topUpAccRepository = topUpAccRepository;
        this.topUpAccDao = topUpAccDao;
        this.userRepository = userRepository;
        this.topUpFactory = topUpFactory;
        this.jwtFilter = jwtFilter;
    }

    @Override
    public ResponseEntity<?> execute() {
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
}
