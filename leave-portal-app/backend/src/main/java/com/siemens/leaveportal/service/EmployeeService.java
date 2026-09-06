package com.siemens.leaveportal.service;

import com.siemens.leaveportal.dto.EmployeeDto;
import com.siemens.leaveportal.model.Employee;
import com.siemens.leaveportal.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    // BUG (N+1): para cada Employee, ".getRequests().size()" forca o
    // Hibernate a fazer 1 query extra para carregar a lista lazy. Com
    // 50 employees na listagem, sao 50 queries so para contar pedidos.
    public List<EmployeeDto> listWithRequestCount() {
        return employeeRepository.findAll().stream()
                .peek(e -> e.getRequests().size()) // side-effect so para forcar load
                .map(e -> EmployeeDto.builder()
                        .id(e.getId()).name(e.getName()).email(e.getEmail())
                        .annualLeaveBalance(e.getAnnualLeaveBalance())
                        .build())
                .toList();
    }
}
