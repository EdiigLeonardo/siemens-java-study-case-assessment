package com.siemens.selfservice.application.usecase;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.in.ListTicketsUseCase;
import com.siemens.selfservice.domain.port.out.CommentRepositoryPort;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListTicketsService implements ListTicketsUseCase {

    private final TicketRepositoryPort ticketRepository;
    private final CommentRepositoryPort commentRepository;

    public ListTicketsService(TicketRepositoryPort ticketRepository, CommentRepositoryPort commentRepository) {
        this.ticketRepository = ticketRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Page<Ticket> list(TicketStatus statusFilter, Pageable pageable) {
        Page<Ticket> page = ticketRepository.findAll(statusFilter, pageable);
        // Para cada ticket da pagina, vamos ao repositorio de comentarios
        // buscar a contagem individualmente. Com 20 tickets por pagina,
        // isto sao 20 queries extra so para mostrar uma lista.
        page.forEach(ticket -> commentRepository.countByTicketId(ticket.getId()));
        return page;
    }
}
