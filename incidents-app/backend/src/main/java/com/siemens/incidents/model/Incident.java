package com.siemens.incidents.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Incident {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String title;

  @Enumerated(EnumType.ORDINAL) // BUG: ORDINAL. Reordenar o enum corrompe dados ja gravados.
  private IncidentStatus status;

  private String internalNotes; // BUG: campo interno, nunca deveria ir na resposta ao cliente.
  private Instant createdAt;

  // BUG: sem cascade/orphanRemoval -> comentarios novos nunca sao gravados so com incident.save().
  @OneToMany(mappedBy = "incident")
  @Builder.Default
  private List<Comment> comments = new ArrayList<>();
}
