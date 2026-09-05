package com.siemens.selfservice.domain.port.out;

import com.siemens.selfservice.domain.model.Comment;
import java.util.List;
import java.util.UUID;

public interface CommentRepositoryPort {
    Comment save(Comment comment);
    List<Comment> findByTicketId(UUID ticketId);
    // Usado (mal) pelo ListTicketsService para contar comentarios - ver ANSWERS.md, item sobre N+1.
    long countByTicketId(UUID ticketId);
}
