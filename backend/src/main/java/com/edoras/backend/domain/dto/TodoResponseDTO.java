package com.edoras.backend.domain.dto;

import lombok.*;

import java.time.Instant;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDTO {

    private Long id;

    private Long createdById;

    private String responsible;

    private String createdByName;

    private Long createdAt;

    private String updatedByName;

    private Long updatedAt;

    private Long expiredAt;

    private String description;
}
