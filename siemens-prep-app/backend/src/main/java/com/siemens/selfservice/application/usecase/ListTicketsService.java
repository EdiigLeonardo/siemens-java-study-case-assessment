package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.in.ListTicketsUseCase;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListTicketsService implements ListTicketsUseCase {

    private final TicketRepositoryPort ticketRepository;

    public ListTicketsService(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Page<Ticket> list(TicketStatus statusFilter, Pageable pageable) {
        return ticketRepository.findAll(statusFilter, pageable);
    }
}
