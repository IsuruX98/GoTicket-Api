package com.csse.eticket.service;

import com.csse.eticket.dao.TicketDao;
import com.csse.eticket.model.Ticket;

import java.util.List;

public interface TicketService {
    Ticket createTicket(TicketDao ticketDao);
    List<Ticket> getAllTickets();
    Ticket getTicketById(int id);
    void deleteTicket(int id);
}

