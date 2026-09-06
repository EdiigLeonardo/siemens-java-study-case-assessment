package com.siemens.selfservice.domain.port.out;

import com.siemens.selfservice.domain.model.Comment;

import java.util.List;
import java.util.UUID;

public interface CommentRepositoryPort {
    Comment save(Comment comment);
    List<Comment> findByTicketId(UUID ticketId);
    long countByTicketId(UUID ticketId);
}
