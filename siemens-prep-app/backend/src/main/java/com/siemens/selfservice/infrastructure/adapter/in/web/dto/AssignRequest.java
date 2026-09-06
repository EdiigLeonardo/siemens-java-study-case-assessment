package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public class AssignRequest {
    @NotBlank
    public String assignee;
}
