package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class UserTodoResponseDTO {

    private Long id;

    private String description;

    private String createdBy;

    private boolean isExpired;

    private Instant expiredAt;
}
