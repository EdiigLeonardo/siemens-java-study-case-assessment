package com.siemens.selfservice.infrastructure.adapter.out.persistence;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class TicketPersistenceAdapter implements TicketRepositoryPort {

    private final TicketJpaRepository jpaRepository;
    private final TicketPersistenceMapper mapper;

    public TicketPersistenceAdapter(TicketJpaRepository jpaRepository, TicketPersistenceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Ticket save(Ticket ticket) {
        TicketJpaEntity saved = jpaRepository.save(mapper.toJpaEntity(ticket));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Ticket> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Page<Ticket> findAll(TicketStatus statusFilter, Pageable pageable) {
        Page<TicketJpaEntity> entities = (statusFilter != null)
                ? jpaRepository.findAllByStatus(statusFilter, pageable)
                : jpaRepository.findAll(pageable);
        return entities.map(mapper::toDomain);
    }
    @Override
    public boolean existsById(UUID id) {
        return jpaRepository.existsById(id);
    }
}
