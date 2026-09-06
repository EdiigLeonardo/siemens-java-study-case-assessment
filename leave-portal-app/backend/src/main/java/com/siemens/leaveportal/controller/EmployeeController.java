package com.siemens.leaveportal.controller;

import com.siemens.leaveportal.dto.EmployeeDto;
import com.siemens.leaveportal.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDto> list() { return employeeService.listWithRequestCount(); }
}
