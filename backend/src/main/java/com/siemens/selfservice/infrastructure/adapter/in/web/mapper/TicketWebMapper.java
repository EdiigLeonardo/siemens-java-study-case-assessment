package com.siemens.selfservice.infrastructure.adapter.in.web.mapper;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.infrastructure.adapter.in.web.dto.TicketResponse;
import org.springframework.stereotype.Component;

@Component
public class TicketWebMapper {

    public TicketResponse toResponse(Ticket ticket) {
        TicketResponse response = new TicketResponse();
        response.id = ticket.getId();
        response.title = ticket.getTitle();
        response.description = ticket.getDescription();
        response.status = ticket.getStatus();
        response.priority = ticket.getPriority();
        response.requesterEmail = ticket.getRequesterEmail();
        response.assignedTo = ticket.getAssignedTo();
        response.createdAt = ticket.getCreatedAt();
        response.updatedAt = ticket.getUpdatedAt();
        return response;
    }
}
