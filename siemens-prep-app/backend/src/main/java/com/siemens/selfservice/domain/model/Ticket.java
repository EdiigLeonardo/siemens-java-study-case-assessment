package com.siemens.selfservice.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Objeto de dominio puro - sem anotacoes JPA/Spring.
 * Representa um pedido (ticket) no self-service portal.
 */
public class Ticket {

    private final UUID id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private String requesterEmail;
    private String assignedTo;
    private final Instant createdAt;
    private Instant updatedAt;

    public Ticket(UUID id, String title, String description, TicketStatus status,
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

    public static Ticket createNew(String title, String description, TicketPriority priority, String requesterEmail) {
        Instant now = Instant.now();
        return new Ticket(UUID.randomUUID(), title, description, TicketStatus.OPEN, priority,
                requesterEmail, null, now, now);
    }

    public void changeStatus(TicketStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = Instant.now();
    }

    public void assignTo(String assignee) {
        this.assignedTo = assignee;
        this.updatedAt = Instant.now();
    }

    // Compara urgencia usando a posicao do enum (ordinal). Funciona hoje,
    // mas e fragil: se alguem reordenar o enum TicketPriority, esta logica
    // muda de comportamento sem que ninguem mexa nesta classe.
    public boolean isMoreUrgentThan(Ticket other) {
        return this.priority.getWeight() > other.priority.getWeight();
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

    // equals() implementado com base no id - mas hashCode() nao foi
    // sobreposto. Repara no que isso faz a um Ticket dentro de um HashSet/HashMap.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
