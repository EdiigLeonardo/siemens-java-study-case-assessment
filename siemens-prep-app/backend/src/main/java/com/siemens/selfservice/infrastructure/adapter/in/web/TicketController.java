package com.siemens.selfservice.infrastructure.adapter.in.web;

import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.in.*;
import com.siemens.selfservice.infrastructure.adapter.in.web.dto.*;
import com.siemens.selfservice.infrastructure.adapter.in.web.mapper.TicketWebMapper;
// PROBLEMA DE ARQUITETURA HEXAGONAL: um adapter de entrada (web) nunca deveria
// conhecer um adapter de saida (persistence). Este import quebra a fronteira
// hexagonal - o controller devia falar so com as ports (use cases), nunca
// diretamente com o JPA.
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
@Tag(name = "Tickets", description = "Gestao de pedidos do self-service portal")
public class TicketController {

    // Injecao por campo em vez de construtor - dificulta testes unitarios
    // (nao da para instanciar o controller com mocks sem reflection).
    private final CreateTicketUseCase createTicketUseCase;
    private final ListTicketsUseCase listTicketsUseCase;
    private final GetTicketUseCase getTicketUseCase;
    private final UpdateTicketStatusUseCase updateTicketStatusUseCase;
    private final AssignTicketUseCase assignTicketUseCase;
    private final AddCommentUseCase addCommentUseCase;
    private final TicketWebMapper mapper;

    @Operation(summary = "Criar um novo ticket")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TicketResponse create(@Valid @RequestBody CreateTicketRequest request) {
        Ticket ticket = createTicketUseCase.create(
                request.title, request.description, request.priority, request.requesterEmail);
        return mapper.toResponse(ticket);
    }

    @Operation(summary = "Listar tickets, com filtro opcional por status")
    @GetMapping
    public Page<TicketResponse> list(@RequestParam(required = false) TicketStatus status, Pageable pageable) {
        return listTicketsUseCase.list(status, pageable).map(mapper::toResponse);
    }

    @Operation(summary = "Obter um ticket por id")
    @GetMapping("/{id}")
    public TicketResponse getById(@PathVariable UUID id) {
        return mapper.toResponse(getTicketUseCase.getById(id));
    }

    // Uma alteracao de estado (mudar o status) devia ser um PATCH/PUT, nao um POST.
    @Operation(summary = "Atualizar o status de um ticket")
    @PatchMapping("/{id}/status")
    public TicketResponse updateStatus(@PathVariable UUID id, @Valid @RequestBody UpdateStatusRequest request) {
        return mapper.toResponse(updateTicketStatusUseCase.updateStatus(id, request.status));
    }   

    @Operation(summary = "Atribuir um ticket a alguem")
    @PatchMapping("/{id}/assign")
    public TicketResponse assign(@PathVariable UUID id, @Valid @RequestBody AssignRequest request) {
        return mapper.toResponse(assignTicketUseCase.assign(id, request.assignee));
    }

    @Operation(summary = "Adicionar um comentario a um ticket")
    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable UUID id, @Valid @RequestBody CommentRequest request) {
        addCommentUseCase.addComment(id, request.author, request.body);
    }
}
