package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import com.siemens.selfservice.domain.model.Comment;
import com.siemens.selfservice.domain.port.out.CommentRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommentPersistenceAdapter implements CommentRepositoryPort {

    private final CommentJpaRepository jpaRepository;

    public CommentPersistenceAdapter(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Comment save(Comment comment) {
        CommentJpaEntity saved = jpaRepository.save(new CommentJpaEntity(
                comment.getId(), comment.getTicketId(), comment.getAuthor(), comment.getBody(), comment.getCreatedAt()));
        return new Comment(saved.getId(), saved.getTicketId(), saved.getAuthor(), saved.getBody(), saved.getCreatedAt());
    }

    @Override
    public List<Comment> findByTicketId(UUID ticketId) {
        return jpaRepository.findByTicketId(ticketId).stream()
                .map(e -> new Comment(e.getId(), e.getTicketId(), e.getAuthor(), e.getBody(), e.getCreatedAt()))
                .toList();
    }

    @Override
    public long countByTicketId(UUID ticketId) {
        return jpaRepository.countByTicketId(ticketId);
    }
}
