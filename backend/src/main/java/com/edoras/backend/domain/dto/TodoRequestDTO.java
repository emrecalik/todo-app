package com.edoras.backend.domain.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDTO {

    @NotBlank
    @Size(min = 4, max = 50)
    private String description;

    @NotNull
    @Max(7)
    private Integer todoDays;

    @NotNull
    @Max(24)
    private Integer todoHours;

    @NotBlank
    @Size(min = 2, max = 50)
    private String userName;
}
