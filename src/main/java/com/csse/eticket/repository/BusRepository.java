package com.csse.eticket.repository;

import com.csse.eticket.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusRepository extends JpaRepository<Bus, String> {
    @Query("SELECT b FROM Bus b WHERE b.busNo= :busNo")
    Bus findByBusNo(@Param("busNo") String busNo);
}
