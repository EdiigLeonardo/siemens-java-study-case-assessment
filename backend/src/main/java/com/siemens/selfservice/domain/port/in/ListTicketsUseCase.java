package com.siemens.selfservice.domain.port.in;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ListTicketsUseCase {
    Page<Ticket> list(TicketStatus statusFilter, Pageable pageable);
}
