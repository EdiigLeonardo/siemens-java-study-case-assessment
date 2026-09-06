package com.siemens.incidents.command;
import com.siemens.incidents.model.Incident;
import com.siemens.incidents.model.IncidentStatus;
import com.siemens.incidents.repository.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class EscalateIncidentHandler {
  private final IncidentRepository repository;
  public EscalateIncidentHandler(IncidentRepository repository) { this.repository = repository; }

  public Incident handle(Long id) {
    Incident i = repository.findById(id).orElseThrow();
    i.setStatus(IncidentStatus.ESCALATED);
    return repository.save(i);
  }
}
