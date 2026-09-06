package com.siemens.booking.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Booking {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @ManyToOne @JoinColumn(name = "equipment_id") private Equipment equipment;
  private String requesterEmail;
  private LocalDateTime startTime; // BUG: sem timezone (ver coluna na migration) -> ambiguo entre servidores/clientes.
  private LocalDateTime endTime;
  @Enumerated(EnumType.STRING) private BookingStatus status;
}
