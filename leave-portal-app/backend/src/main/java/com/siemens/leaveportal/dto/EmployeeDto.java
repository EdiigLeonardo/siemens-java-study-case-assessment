package com.siemens.leaveportal.dto;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class EmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Integer annualLeaveBalance;
    // BUG: nao inclui "role". A UI de manager acaba sem forma de saber
    // quem e manager sem chamar outro endpoint.
}
