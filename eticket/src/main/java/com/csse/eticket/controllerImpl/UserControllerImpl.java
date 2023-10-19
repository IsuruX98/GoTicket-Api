package com.csse.eticket.controllerImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.controller.UserController;
import com.csse.eticket.dao.users.AccDao;
import com.csse.eticket.model.users.User;
import com.csse.eticket.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<?> signUp(AccDao accDao) {
        try {
            return userService.signUp(accDao);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> login(User user) {
        try {
            return userService.login(user);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        try{
            return userService.getAllUser();
        }catch(Exception ex){
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> checkToken() {
        try {
            return userService.checkToken();
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return ResponseEntity.internalServerError().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }
}
