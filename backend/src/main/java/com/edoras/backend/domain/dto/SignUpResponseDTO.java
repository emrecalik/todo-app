package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class SignUpResponseDTO {
    private boolean successful;

    private Long id;

    private String name;

    private String userName;

    private String email;
}
