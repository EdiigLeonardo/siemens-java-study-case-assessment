package com.siemens.leaveportal.dto;

import com.siemens.leaveportal.model.LeaveStatus;
import com.siemens.leaveportal.model.LeaveType;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class LeaveResponseDto {
    private Long id;
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LeaveType type;
    private LeaveStatus status;
    private String reason;
}
