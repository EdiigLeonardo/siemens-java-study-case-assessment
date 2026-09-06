package com.siemens.booking.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Equipment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  private String name;
  @OneToMany(mappedBy = "equipment") private List<Booking> bookings;
}
