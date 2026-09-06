package com.siemens.selfservice.application;

import com.siemens.selfservice.application.usecase.CreateTicketService;
import com.siemens.selfservice.domain.model.Ticket;
import com.siemens.selfservice.domain.model.TicketPriority;
import com.siemens.selfservice.domain.port.out.TicketRepositoryPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateTicketServiceTest {

    private final TicketRepositoryPort repository = mock(TicketRepositoryPort.class);
    private final CreateTicketService service = new CreateTicketService(repository);

    @Test
    void deveCriarTicketComStatusOpen() {
        when(repository.save(any(Ticket.class))).thenAnswer(inv -> inv.getArgument(0));

        Ticket ticket = service.create("Impressora sem tinteiro", "Piso 3", TicketPriority.MEDIUM, "edig@example.com");

        assertThat(ticket.getStatus().name()).isEqualTo("OPEN");
        verify(repository, times(1)).save(any(Ticket.class));
    }

    // TODO (exercicio): falta um teste que garanta que um ticket criado com
    // titulo vazio e rejeitado. Neste momento o teste passaria porque nao
    // ha nenhuma validacao no dominio nem no DTO - e um dos bugs a corrigir.
}
