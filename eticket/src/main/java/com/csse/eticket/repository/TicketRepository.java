package com.csse.eticket.repository;

import com.csse.eticket.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query("SELECT d FROM Ticket d ORDER BY d.id DESC")
    List<Ticket> getTicketsDesc();
}
