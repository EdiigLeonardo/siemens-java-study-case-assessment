package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketPriority;
import com.siemens.selfservice.domain.port.in.CreateTicketUseCase;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class CreateTicketService implements CreateTicketUseCase {

    private final TicketRepositoryPort ticketRepository;

    public CreateTicketService(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket create(String title, String description, TicketPriority priority, String requesterEmail) {
        Ticket ticket = Ticket.createNew(title, description, priority, requesterEmail);
        return ticketRepository.save(ticket);
    }
}
