package com.edoras.backend.domain.dto;

import com.edoras.backend.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;

    @NotBlank
    @Size(min = 4, max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    private Set<Role> roles = new HashSet<>();
}
