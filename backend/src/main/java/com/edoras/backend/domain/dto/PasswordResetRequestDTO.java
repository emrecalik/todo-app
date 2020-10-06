package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class PasswordResetRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 4, max = 50)
    @Email
    private String email;
}
