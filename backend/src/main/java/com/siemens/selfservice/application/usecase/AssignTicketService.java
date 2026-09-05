package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.exception.TicketNotFoundException;
import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.port.in.AssignTicketUseCase;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssignTicketService implements AssignTicketUseCase {

    private final TicketRepositoryPort ticketRepository;

    public AssignTicketService(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket assign(UUID id, String assignee) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        ticket.assignTo(assignee);
        return ticketRepository.save(ticket);
    }
}
