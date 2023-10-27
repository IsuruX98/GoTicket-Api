package com.csse.eticket.controller;

import com.csse.eticket.dao.TicketDao;
import com.csse.eticket.model.Ticket;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tickets")
public interface TicketController {
    @PostMapping
    ResponseEntity<Ticket> createTicket(@RequestBody TicketDao ticketDao);

    @GetMapping
    ResponseEntity<List<Ticket>> getAllTickets();

    @GetMapping("/{id}")
    ResponseEntity<Ticket> getTicketById(@PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTicket(@PathVariable int id);

    @GetMapping("/last")
    ResponseEntity<?> getLastTicket();
}
