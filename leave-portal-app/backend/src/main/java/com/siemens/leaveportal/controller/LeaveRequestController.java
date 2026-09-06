package com.siemens.leaveportal.controller;

import com.siemens.leaveportal.dto.*;
import com.siemens.leaveportal.exception.InsufficientLeaveBalanceException;
import com.siemens.leaveportal.model.LeaveStatus;
import com.siemens.leaveportal.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    // BUG: DTO tem @NotNull/@FutureOrPresent mas falta @Valid aqui.
    // As anotacoes de validacao existem e nunca sao acionadas.
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LeaveResponseDto create(@RequestBody CreateLeaveRequestDto dto) throws InsufficientLeaveBalanceException {
        return leaveRequestService.create(dto);
    }

    @GetMapping
    public Page<LeaveResponseDto> list(@RequestParam Long employeeId,
                                        @RequestParam(required = false) LeaveStatus status,
                                        Pageable pageable) {
        return leaveRequestService.listByEmployee(employeeId, status, pageable);
    }

    // BUG (Spring Security): hasRole('MANAGER') exige a authority "ROLE_MANAGER".
    // O SecurityConfig atribui a authority como "MANAGER" (sem prefixo) -
    // um manager autenticado recebe sempre 403 aqui.
    @PreAuthorize("hasRole('MANAGER')")
    @PatchMapping("/{id}/decision")
    public LeaveResponseDto decide(@PathVariable Long id, @RequestBody ApproveRejectDto dto) {
        return leaveRequestService.decide(id, dto.getApproved());
    }
}
