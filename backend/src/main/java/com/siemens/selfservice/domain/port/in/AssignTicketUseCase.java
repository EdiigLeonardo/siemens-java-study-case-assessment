package com.siemens.selfservice.domain.port.in;

import com.siemens.selfservice.domain.model.Ticket;
import java.util.UUID;

public interface AssignTicketUseCase {
    Ticket assign(UUID id, String assignee);
}
