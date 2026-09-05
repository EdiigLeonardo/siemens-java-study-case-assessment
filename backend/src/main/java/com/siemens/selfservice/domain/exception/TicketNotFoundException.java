package com.siemens.selfservice.domain.exception;

import java.util.UUID;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(UUID id) {
        super("Ticket nao encontrado: " + id);
    }
}
