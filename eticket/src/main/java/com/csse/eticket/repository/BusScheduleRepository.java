package com.csse.eticket.repository;

import com.csse.eticket.model.Bus;
import com.csse.eticket.model.BusSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Integer> {
    @Query("SELECT bs FROM BusSchedule bs WHERE bs.scheduleId= :scheduleId")
    Optional<BusSchedule> findByScheduleId(@Param("scheduleId") Integer scheduleId);
}
