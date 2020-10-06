package com.edoras.backend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;
}
