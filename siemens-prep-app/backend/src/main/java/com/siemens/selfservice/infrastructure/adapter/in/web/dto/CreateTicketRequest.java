package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import com.siemens.selfservice.domain.model.TicketPriority;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// Reparar: nao ha nenhuma anotacao de validacao aqui (@NotBlank, @Size, @Email...).
// Neste momento e possivel criar um ticket com titulo vazio ou email invalido.
public class CreateTicketRequest {
    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    public String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    public String description;

    @NotNull(message = "Priority is required")
    public TicketPriority priority;

    @NotBlank(message = "Requester email is required")
    @Email(message = "Invalid email address")
    public String requesterEmail;
}
