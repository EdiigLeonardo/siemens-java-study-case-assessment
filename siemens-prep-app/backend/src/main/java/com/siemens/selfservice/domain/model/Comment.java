package com.siemens.selfservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Comment {
    private final UUID id;
    private final UUID ticketId;
    private final String author;
    private final String body;
    private final Instant createdAt;

    public Comment(UUID id, UUID ticketId, String author, String body, Instant createdAt) {
        this.id = id;
        this.ticketId = ticketId;
        this.author = author;
        this.body = body;
        this.createdAt = createdAt;
    }

    public static Comment createNew(UUID ticketId, String author, String body) {
        return new Comment(UUID.randomUUID(), ticketId, author, body, Instant.now());
    }

    public UUID getId() { return id; }
    public UUID getTicketId() { return ticketId; }
    public String getAuthor() { return author; }
    public String getBody() { return body; }
    public Instant getCreatedAt() { return createdAt; }
}
