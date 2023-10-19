package com.csse.eticket.controllerImpl;

import com.csse.eticket.dao.TicketDao;
import com.csse.eticket.model.Ticket;
import com.csse.eticket.service.TicketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TicketControllerImplTest {

    @Mock
    private TicketService ticketService;

    @InjectMocks
    private TicketControllerImpl ticketController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createTicket() {
        TicketDao ticketDao = new TicketDao();
        Ticket ticket = new Ticket();
        when(ticketService.createTicket(ticketDao)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.createTicket(ticketDao);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
        verify(ticketService, times(1)).createTicket(ticketDao);
    }

    @Test
    void getAllTickets() {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        List<Ticket> tickets = Arrays.asList(ticket1, ticket2);
        when(ticketService.getAllTickets()).thenReturn(tickets);

        ResponseEntity<List<Ticket>> response = ticketController.getAllTickets();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tickets, response.getBody());
        verify(ticketService, times(1)).getAllTickets();
    }

    @Test
    void getTicketById() {
        int ticketId = 1;
        Ticket ticket = new Ticket();
        when(ticketService.getTicketById(ticketId)).thenReturn(ticket);

        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ticket, response.getBody());
        verify(ticketService, times(1)).getTicketById(ticketId);
    }

    @Test
    void getTicketByIdNotFound() {
        int ticketId = 1;
        when(ticketService.getTicketById(ticketId)).thenReturn(null);

        ResponseEntity<Ticket> response = ticketController.getTicketById(ticketId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(ticketService, times(1)).getTicketById(ticketId);
    }

    @Test
    void deleteTicket() {
        int ticketId = 1;

        ResponseEntity<Void> response = ticketController.deleteTicket(ticketId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(ticketService, times(1)).deleteTicket(ticketId);
    }
}
