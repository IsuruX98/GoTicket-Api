package com.csse.eticket.repository.topups;

import com.csse.eticket.model.topup.TopUpAcc;
import com.csse.eticket.model.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TopUpAccRepository extends JpaRepository<TopUpAcc, Integer> {
    @Query("SELECT a FROM TopUpAcc a WHERE a.user= :currentUser")
    List<TopUpAcc> findAllByUser(@Param("currentUser") User currentUser);
    @Query("SELECT a FROM TopUpAcc a WHERE a.id = :accId")
    TopUpAcc findByAccId(@Param("accId")Integer accId);
}
