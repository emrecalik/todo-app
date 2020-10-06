package com.edoras.backend.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BaseErrorModel {
    private boolean success;
    private String description;
}
