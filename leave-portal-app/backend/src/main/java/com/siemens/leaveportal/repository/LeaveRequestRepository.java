package com.siemens.leaveportal.repository;

import com.siemens.leaveportal.model.LeaveRequest;
import com.siemens.leaveportal.model.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    // BUG (JPQL): recebe "status" mas a query nunca o usa no WHERE.
    // Devolve sempre TODOS os pedidos da pagina, ignorando o filtro.
    @Query("SELECT l FROM LeaveRequest l WHERE l.employee.id = :employeeId")
    Page<LeaveRequest> findByEmployeeAndStatus(@Param("employeeId") Long employeeId,
                                                @Param("status") LeaveStatus status,
                                                Pageable pageable);

    // BUG: sem limite de tamanho de pagina configurado a nivel global
    // (spring.data.web.pageable.max-page-size) - um cliente pode pedir
    // size=999999 e forcar a app a carregar a tabela toda.
    Page<LeaveRequest> findAll(Pageable pageable);
}
