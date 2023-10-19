package com.csse.eticket.repository;

import com.csse.eticket.model.Recharge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RechargeRepository extends JpaRepository<Recharge, Integer> {
    @Query("SELECT r FROM Recharge r WHERE r.topUpAcc.user.id= :id")
    List<Recharge> findRechargesByUserId(@Param("id") Integer id);
}
