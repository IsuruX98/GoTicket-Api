package com.csse.eticket.controllerImpl;

import com.csse.eticket.controller.TicketController;
import com.csse.eticket.dao.TicketDao;
import com.csse.eticket.model.Ticket;
import com.csse.eticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketControllerImpl implements TicketController {

    private final TicketService ticketService;

    @Autowired
    public TicketControllerImpl(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDao ticketDao) {
        Ticket ticket = ticketService.createTicket(ticketDao);
        return ResponseEntity.ok(ticket);
    }

    @Override
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @Override
    public ResponseEntity<Ticket> getTicketById(int id) {
        Ticket ticket = ticketService.getTicketById(id);
        if (ticket != null) {
            return ResponseEntity.ok(ticket);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteTicket(int id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}


