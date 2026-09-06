package com.siemens.incidents.command;
import com.siemens.incidents.model.Incident;
import com.siemens.incidents.model.IncidentStatus;
import com.siemens.incidents.notification.NotificationDispatcher;
import com.siemens.incidents.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Service
public class CreateIncidentHandler {
  private final IncidentRepository repository;
  private final NotificationDispatcher dispatcher; // ver bug de ciclo em NotificationDispatcher

  public CreateIncidentHandler(IncidentRepository repository, NotificationDispatcher dispatcher) {
    this.repository = repository;
    this.dispatcher = dispatcher;
  }

  public Incident handle(String title) {
    Incident saved = repository.save(Incident.builder().title(title).status(IncidentStatus.OPEN).createdAt(Instant.now()).build());
    dispatcher.notifyCreated(title);
    return saved;
  }
}
