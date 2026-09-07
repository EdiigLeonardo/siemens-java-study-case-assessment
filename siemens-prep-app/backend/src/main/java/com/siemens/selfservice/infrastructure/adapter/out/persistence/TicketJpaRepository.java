package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.siemens.selfservice.domain.model.TicketStatus;

import java.util.UUID;

public interface TicketJpaRepository extends JpaRepository<TicketJpaEntity, UUID> {
    Page<TicketJpaEntity> findAllByStatus(TicketStatus status, Pageable pageable);
}
