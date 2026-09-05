package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import com.siemens.selfservice.domain.model.TicketPriority;
import com.siemens.selfservice.domain.model.TicketStatus;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "tickets")
public class TicketJpaEntity {

    @Id
    private UUID id;

    private String title;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    private String requesterEmail;
    private String assignedTo;
    private Instant createdAt;
    private Instant updatedAt;

    protected TicketJpaEntity() {
        // exigido pelo JPA
    }

    public TicketJpaEntity(UUID id, String title, String description, TicketStatus status,
                            TicketPriority priority, String requesterEmail, String assignedTo,
                            Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.requesterEmail = requesterEmail;
        this.assignedTo = assignedTo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TicketStatus getStatus() { return status; }
    public TicketPriority getPriority() { return priority; }
    public String getRequesterEmail() { return requesterEmail; }
    public String getAssignedTo() { return assignedTo; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
