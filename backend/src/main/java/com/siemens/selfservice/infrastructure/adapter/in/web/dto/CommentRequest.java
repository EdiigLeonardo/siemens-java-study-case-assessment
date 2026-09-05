package com.siemens.selfservice.infrastructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public class CommentRequest {
    @NotBlank
    public String author;
    @NotBlank
    public String body;
}
