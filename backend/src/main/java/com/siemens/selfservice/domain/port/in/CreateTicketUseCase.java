package com.siemens.selfservice.domain.port.in;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketPriority;

public interface CreateTicketUseCase {
    Ticket create(String title, String description, TicketPriority priority, String requesterEmail);
}
