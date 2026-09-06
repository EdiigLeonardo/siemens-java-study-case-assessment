package com.siemens.incidents.model;
import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Comment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name = "incident_id") private Incident incident;
  private String body;
}
