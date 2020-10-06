package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInResponseDTO {

    private boolean successful;

    private String jwt;

    private String userName;

    private Long userId;
}
