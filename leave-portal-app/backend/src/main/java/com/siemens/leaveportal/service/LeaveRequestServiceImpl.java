package com.siemens.leaveportal.service;

import com.siemens.leaveportal.dto.CreateLeaveRequestDto;
import com.siemens.leaveportal.dto.LeaveResponseDto;
import com.siemens.leaveportal.exception.InsufficientLeaveBalanceException;
import com.siemens.leaveportal.exception.LeaveRequestNotFoundException;
import com.siemens.leaveportal.model.*;
import com.siemens.leaveportal.repository.EmployeeRepository;
import com.siemens.leaveportal.repository.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public LeaveResponseDto create(CreateLeaveRequestDto dto) throws InsufficientLeaveBalanceException {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new LeaveRequestNotFoundException(dto.getEmployeeId()));

        long days = ChronoUnit.DAYS.between(dto.getStartDate(), dto.getEndDate()) + 1;
        if (days > employee.getAnnualLeaveBalance()) {
            // Excecao checked dentro de metodo @Transactional -> nao faz rollback (ver classe da excecao).
            throw new InsufficientLeaveBalanceException("Saldo insuficiente");
        }

        LeaveRequest request = LeaveRequest.builder()
                .employee(employee).startDate(dto.getStartDate()).endDate(dto.getEndDate())
                .type(dto.getType()).status(LeaveStatus.PENDING).reason(dto.getReason())
                .createdAt(Instant.now())
                .build();
        LeaveRequest saved = leaveRequestRepository.save(request);

        // BUG: debita logo o saldo ao CRIAR o pedido (ainda PENDING, nem
        // aprovado). Se o manager rejeitar depois, ninguem devolve os dias.
        employee.setAnnualLeaveBalance(employee.getAnnualLeaveBalance() - (int) days);
        employeeRepository.save(employee);

        return toDto(saved);
    }

    @Override
    public Page<LeaveResponseDto> listByEmployee(Long employeeId, LeaveStatus status, Pageable pageable) {
        return leaveRequestRepository.findByEmployeeAndStatus(employeeId, status, pageable).map(this::toDto);
    }

    // BUG: falta @Transactional aqui. approve()/reject() faz DOIS saves
    // (leave request + employee) em chamadas separadas ao repositorio; sem
    // uma transacao a envolver as duas, uma falha a meio deixa dados
    // inconsistentes (pedido aprovado mas saldo nao devolvido, por exemplo).
    public LeaveResponseDto decide(Long id, boolean approved) {
        LeaveRequest request = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new LeaveRequestNotFoundException(id));

        request.setStatus(approved ? LeaveStatus.APPROVED : LeaveStatus.REJECTED);
        leaveRequestRepository.save(request);

        if (!approved) {
            long days = ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate()) + 1;
            Employee employee = request.getEmployee();
            employee.setAnnualLeaveBalance(employee.getAnnualLeaveBalance() + (int) days);
            employeeRepository.save(employee);
        }
        return toDto(request);
    }

    private LeaveResponseDto toDto(LeaveRequest r) {
        return LeaveResponseDto.builder()
                .id(r.getId()).employeeId(r.getEmployee().getId())
                .startDate(r.getStartDate()).endDate(r.getEndDate())
                .type(r.getType()).status(r.getStatus()).reason(r.getReason())
                .build();
    }
}
