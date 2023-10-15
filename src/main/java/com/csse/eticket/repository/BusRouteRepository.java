package com.csse.eticket.repository;

import com.csse.eticket.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BusRouteRepository extends JpaRepository<BusRoute, String> {
    @Query("SELECT br FROM BusRoute br WHERE br.routeName = :routeName")
    BusRoute findByRouteName(@Param("routeName") String routeName);
}
