package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "comments")
public class CommentJpaEntity {

    @Id
    private UUID id;

    private UUID ticketId;
    private String author;

    @Column(length = 4000)
    private String body;

    private Instant createdAt;

    protected CommentJpaEntity() {}

    public CommentJpaEntity(UUID id, UUID ticketId, String author, String body, Instant createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.author = author;
        this.body = body;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public UUID getTicketId() { return ticketId; }
    public String getAuthor() { return author; }
    public String getBody() { return body; }
    public Instant getCreatedAt() { return createdAt; }
}
