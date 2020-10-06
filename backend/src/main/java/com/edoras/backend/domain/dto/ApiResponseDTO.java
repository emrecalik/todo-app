package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponseDTO {
    private boolean successful;

    private String message;
}
