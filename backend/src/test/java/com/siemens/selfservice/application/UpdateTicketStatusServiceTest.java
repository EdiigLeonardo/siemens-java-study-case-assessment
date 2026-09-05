package com.siemens.selfservice.application;

import com.siemens.selfservice.application.usecase.UpdateTicketStatusService;
import com.siemens.selfservice.domain.exception.InvalidStatusTransitionException;
import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketPriority;
import com.siemens.selfservice.domain.model.TicketStatus;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UpdateTicketStatusServiceTest {

    private final TicketRepositoryPort repository = mock(TicketRepositoryPort.class);
    private final UpdateTicketStatusService service = new UpdateTicketStatusService(repository);

    @Test
    void naoDevePermitirReabrirTicketFechado() {
        Ticket closed = Ticket.createNew("X", "Y", TicketPriority.LOW, "a@b.com");
        closed.changeStatus(TicketStatus.CLOSED);
        when(repository.findById(closed.getId())).thenReturn(Optional.of(closed));

        assertThatThrownBy(() -> service.updateStatus(closed.getId(), TicketStatus.OPEN))
                .isInstanceOf(InvalidStatusTransitionException.class);
    }
}
