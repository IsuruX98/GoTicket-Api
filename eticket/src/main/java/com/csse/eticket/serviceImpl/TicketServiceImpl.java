package com.csse.eticket.serviceImpl;

import com.csse.eticket.dao.TicketDao;
import com.csse.eticket.model.Ticket;
import com.csse.eticket.repository.TicketRepository;
import com.csse.eticket.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket createTicket(TicketDao ticketDao) {
        Ticket ticket = new Ticket();
        ticket.setId(ticketDao.getId());
        ticket.setQrData(ticketDao.getQrData());
        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteTicket(int id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public Ticket getTicektsDesc() {
        List<Ticket> ticketList =  ticketRepository.getTicketsDesc();
        return ticketList.get(0);
    }
}
