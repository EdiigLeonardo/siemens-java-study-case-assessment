package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CommentJpaRepository extends JpaRepository<CommentJpaEntity, UUID> {
    List<CommentJpaEntity> findByTicketId(UUID ticketId);
    long countByTicketId(UUID ticketId);
}
