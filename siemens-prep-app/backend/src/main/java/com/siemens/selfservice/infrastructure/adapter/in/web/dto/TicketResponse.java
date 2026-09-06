package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import com.siemens.selfservice.domain.model.TicketPriority;
import com.siemens.selfservice.domain.model.TicketStatus;

import java.time.Instant;
import java.util.UUID;

public class TicketResponse {
    public UUID id;
    public String title;
    public String description;
    public TicketStatus status;
    public TicketPriority priority;
    public String requesterEmail;
    public String assignedTo;
    public Instant createdAt;
    public Instant updatedAt;
}
