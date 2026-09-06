package com.siemens.leaveportal.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

// BUG (Lombok+JPA): @Data numa entidade com relacao bidirecional. Gera
// equals/hashCode/toString que incluem "requests" -> ao chamar toString()
// ou equals() num Employee, o Hibernate tenta carregar TODOS os
// LeaveRequest, e cada LeaveRequest (tambem @Data) volta a chamar
// toString() no Employee -> StackOverflowError.
@Entity
@Table(name = "employees")
@Data
@Builder
public class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer annualLeaveBalance;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<LeaveRequest> requests;

    // BUG: so tem @Builder -> Lombok gera so o construtor all-args (privado,
    // usado pelo builder). Falta @NoArgsConstructor: o Hibernate PRECISA de
    // um construtor sem argumentos para instanciar a entidade via reflection.
}
