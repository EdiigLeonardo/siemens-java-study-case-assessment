package com.siemens.selfservice.domain.port.out;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepositoryPort {
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(UUID id);
    Page<Ticket> findAll(TicketStatus statusFilter, Pageable pageable);
    boolean existsById(UUID id);
}
