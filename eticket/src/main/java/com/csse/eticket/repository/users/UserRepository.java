package com.csse.eticket.repository.users;

import com.csse.eticket.dao.users.UserDao;
import com.csse.eticket.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmailId(@Param("email") String email);

    List<UserDao> getAllUser();

    List<String> getAllAdmin();

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = 'inspector'")
    List<User> getPassengers();
}
