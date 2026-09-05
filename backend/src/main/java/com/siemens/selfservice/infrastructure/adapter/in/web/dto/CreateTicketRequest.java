package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import com.siemens.selfservice.domain.model.TicketPriority;

// Reparar: nao ha nenhuma anotacao de validacao aqui (@NotBlank, @Size, @Email...).
// Neste momento e possivel criar um ticket com titulo vazio ou email invalido.
public class CreateTicketRequest {
    public String title;
    public String description;
    public TicketPriority priority;
    public String requesterEmail;
}
