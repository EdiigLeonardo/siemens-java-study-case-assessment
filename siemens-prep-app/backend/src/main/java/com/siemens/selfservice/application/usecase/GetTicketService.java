package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.exception.TicketNotFoundException;
import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.port.in.GetTicketUseCase;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetTicketService implements GetTicketUseCase {

    private final TicketRepositoryPort ticketRepository;

    public GetTicketService(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket getById(UUID id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }
}
