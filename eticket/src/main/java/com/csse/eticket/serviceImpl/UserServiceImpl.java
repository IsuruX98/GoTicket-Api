package com.csse.eticket.serviceImpl;

import com.csse.eticket.constants.ETicketConstants;
import com.csse.eticket.dao.users.AccDao;
import com.csse.eticket.jwt.CustomerUserDetailsService;
import com.csse.eticket.jwt.JwtFilter;
import com.csse.eticket.jwt.JwtUtil;
import com.csse.eticket.model.users.*;
import com.csse.eticket.repository.users.UserRepository;
import com.csse.eticket.service.UserService;
import com.csse.eticket.util.TravelInnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private final UserFactory userFactory;

    public UserServiceImpl(UserFactory userFactory) {
        this.userFactory = userFactory;
    }

    @Override
    public ResponseEntity<?> signUp(AccDao accDao) {
        try {
            if (validateSignUpMap(accDao)) {
                User findUser = userRepo.findByEmailId(accDao.getEmail());
                if (findUser != null) {
                    return ResponseEntity.badRequest().body("Email already exists");
                }

                if (Objects.isNull(findUser)) {
                    User newUser = userFactory.createUser(accDao.getRole());
                    newUser.setName(accDao.getName());
                    newUser.setEmail(accDao.getEmail());
                    newUser.setContactNumber(accDao.getContactNumber());

                    // Hash the user's password before saving it
                    String hashedPassword = passwordEncoder.encode(accDao.getPassword());
                    newUser.setPassword(hashedPassword);

                    newUser.setRole(accDao.getRole());

                    if (newUser instanceof Manager) {
                        ((Manager) newUser).setEmployeeId(accDao.getUniqueField());
                    } else if (newUser instanceof Passenger) {
                        ((Passenger) newUser).setNic(accDao.getUniqueField());
                    } else if (newUser instanceof Foreigner) {
                        ((Foreigner) newUser).setPassportNo(accDao.getUniqueField());
                    } else if (newUser instanceof Inspector) {
                        ((Inspector) newUser).setBusNo(accDao.getUniqueField());
                    }

                    return ResponseEntity.ok(userRepo.save(newUser));
                } else {
                    return ResponseEntity.badRequest().body("Try Again! Error occurred while creating an account");
                }
            } else {
                return ResponseEntity.badRequest().body("Check your data.");
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), Level.SEVERE);
            return ResponseEntity.badRequest().body("Try Again! Error occurred while creating an account");
        }
    }

    private boolean validateSignUpMap(AccDao accDao) {
        return !accDao.getName().isEmpty() && !accDao.getContactNumber().isEmpty()
                && !accDao.getEmail().isEmpty() && !accDao.getPassword().isEmpty();
    }

    @Override
    public ResponseEntity<?> login(User user) {
        try {
            // Retrieve the user from the database based on their email
            User dbUser = userRepo.findByEmailId(user.getEmail());

            if (dbUser != null) {
                // Use the same hashing algorithm to hash the input password
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
                    // Passwords match, user is authenticated

                    // Generate JWT token
                    String token = jwtUtil.generateToken(dbUser.getEmail(), dbUser.getRole());

                    Map<String, String> response = new HashMap<>();
                    response.put("token", token);

                    return ResponseEntity.ok(response);
                }
            }

            return ResponseEntity.badRequest().body(ETicketConstants.INVALID_DATA);
        } catch (Exception ex) {
            log.error(ex.getMessage(), Level.SEVERE);
        }

        return ResponseEntity.badRequest().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }


    @Override
    public ResponseEntity<?> getAllUser() {
        try {
            if (jwtFilter.isManager()) {
                log.info("asdsd");
                return ResponseEntity.ok().body(userRepo.getAllUser());
            } else {
                return ResponseEntity.badRequest().body(ETicketConstants.UNAUTHORIZED_ACCESS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage(), Level.SEVERE);
        }
        return ResponseEntity.badRequest().body(ETicketConstants.SOMETHING_WENT_WRONG);
    }

    @Override
    public ResponseEntity<?> checkToken() {
        return ResponseEntity.ok().body("true");
    }

    @Override
    public ResponseEntity<?> changePassword(Map<String, String> requestMap) {
        try {
            User userObj = userRepo.findByEmail(jwtFilter.getCurrentUser());
            if (userObj != null) {
                if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {
                    userObj.setPassword(requestMap.get("newPassword"));
                    userRepo.save(userObj);
                    return TravelInnUtils.getResponseEntity("Password Updated Successfully.", HttpStatus.OK);
                }
                return TravelInnUtils.getResponseEntity("Incorrect Old Password.", HttpStatus.MULTI_STATUS);
            }
            return TravelInnUtils.getResponseEntity(ETicketConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception ex) {
            log.error(ex.getMessage(), Level.SEVERE);
        }
        return TravelInnUtils.getResponseEntity(ETicketConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
