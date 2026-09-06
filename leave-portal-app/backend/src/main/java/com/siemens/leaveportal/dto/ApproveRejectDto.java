package com.siemens.leaveportal.dto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ApproveRejectDto {
    @NotNull
    private Boolean approved;
}
