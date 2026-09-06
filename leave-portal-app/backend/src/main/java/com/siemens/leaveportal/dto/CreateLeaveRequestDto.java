package com.siemens.leaveportal.dto;

import com.siemens.leaveportal.model.LeaveType;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateLeaveRequestDto {
    @NotNull
    private Long employeeId;
    @NotNull @FutureOrPresent
    private LocalDate startDate;
    @NotNull @FutureOrPresent
    private LocalDate endDate;
    // BUG: nao ha validacao "endDate >= startDate" (nem aqui nem no service).
    @NotNull
    private LeaveType type;
    private String reason;
}
