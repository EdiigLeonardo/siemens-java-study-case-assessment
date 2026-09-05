package com.siemens.selfservice.domain.port.in;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import java.util.UUID;

public interface UpdateTicketStatusUseCase {
    Ticket updateStatus(UUID id, TicketStatus newStatus);
}
