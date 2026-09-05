package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import com.siemens.selfservice.domain.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketPersistenceMapper {

    public TicketJpaEntity toJpaEntity(Ticket ticket) {
        return new TicketJpaEntity(
                ticket.getId(), ticket.getTitle(), ticket.getDescription(), ticket.getStatus(),
                ticket.getPriority(), ticket.getRequesterEmail(), ticket.getAssignedTo(),
                ticket.getCreatedAt(), ticket.getUpdatedAt());
    }

    public Ticket toDomain(TicketJpaEntity entity) {
        return new Ticket(
                entity.getId(), entity.getTitle(), entity.getDescription(), entity.getStatus(),
                entity.getPriority(), entity.getRequesterEmail(), entity.getAssignedTo(),
                entity.getCreatedAt(), entity.getUpdatedAt());
    }
}
