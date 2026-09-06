package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import com.siemens.selfservice.domain.model.TicketStatus;
import jakarta.validation.constraints.NotNull;

public class UpdateStatusRequest {
    @NotNull
    public TicketStatus status;
}
