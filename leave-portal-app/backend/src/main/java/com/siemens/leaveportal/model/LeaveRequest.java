package com.siemens.leaveportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.time.LocalDate;

// Mesmo problema de @Data + relacao bidirecional (ver Employee.java).
// Falta tambem @Version: duas aprovacoes em simultaneo ao mesmo pedido
// (lost update) nao sao detetadas - sem controlo de concorrencia otimista.
@Entity
@Table(name = "leave_requests")
@Data
@Builder
public class LeaveRequest {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private LeaveType type;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    private String reason;
    private Instant createdAt;
}
