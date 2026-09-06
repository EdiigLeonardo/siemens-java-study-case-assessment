package com.siemens.leaveportal.service;

import com.siemens.leaveportal.dto.CreateLeaveRequestDto;
import com.siemens.leaveportal.dto.LeaveResponseDto;
import com.siemens.leaveportal.exception.InsufficientLeaveBalanceException;
import com.siemens.leaveportal.model.LeaveStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LeaveRequestService {
    LeaveResponseDto create(CreateLeaveRequestDto dto) throws InsufficientLeaveBalanceException;
    Page<LeaveResponseDto> listByEmployee(Long employeeId, LeaveStatus status, Pageable pageable);
    LeaveResponseDto decide(Long id, boolean approved);
}
