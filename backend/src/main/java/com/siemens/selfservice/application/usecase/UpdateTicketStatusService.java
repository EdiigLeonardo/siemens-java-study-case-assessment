package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.exception.InvalidStatusTransitionException;
import com.siemens.selfservice.domain.exception.TicketNotFoundException;
import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.in.UpdateTicketStatusUseCase;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UpdateTicketStatusService implements UpdateTicketStatusUseCase {

    private final TicketRepositoryPort ticketRepository;

    public UpdateTicketStatusService(TicketRepositoryPort ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket updateStatus(UUID id, TicketStatus newStatus) {
        Ticket ticket = validateAndFetch(id, newStatus);
        ticket.changeStatus(newStatus);
        return ticketRepository.save(ticket);
    }

    // @Transactional aqui nao tem qualquer efeito pratico: e chamado a partir
    // de updateStatus() na MESMA instancia (this.validateAndFetch(...)),
    // por isso nunca passa pelo proxy do Spring que aplicaria a transacao.
    @Transactional
    public Ticket validateAndFetch(UUID id, TicketStatus newStatus) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
        if (ticket.getStatus() == TicketStatus.CLOSED && newStatus != TicketStatus.CLOSED) {
            throw new InvalidStatusTransitionException("Nao e possivel reabrir um ticket fechado");
        }
        return ticket;
    }
}
