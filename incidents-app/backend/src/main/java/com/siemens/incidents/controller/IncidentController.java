package com.siemens.incidents.controller;
import com.siemens.incidents.command.CreateIncidentHandler;
import com.siemens.incidents.command.EscalateIncidentHandler;
import com.siemens.incidents.dto.IncidentDto;
import com.siemens.incidents.model.Incident;
import com.siemens.incidents.repository.IncidentRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
  private final CreateIncidentHandler createHandler;
  private final EscalateIncidentHandler escalateHandler;
  private final IncidentRepository repository;

  public IncidentController(CreateIncidentHandler c, EscalateIncidentHandler e, IncidentRepository r) {
    this.createHandler = c; this.escalateHandler = e; this.repository = r;
  }

  @PostMapping
  public Incident create(@RequestBody IncidentDto dto) { return createHandler.handle(dto.getTitle()); }

  @GetMapping
  public List<Incident> list() { return repository.findAll(); } // entidade direta, mesma familia do bug do IncidentDto

  @PatchMapping("/{id}/escalate")
  public Incident escalate(@PathVariable Long id) { return escalateHandler.handle(id); }
}
