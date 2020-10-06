package com.edoras.backend.domain.dto;

import com.edoras.backend.domain.Role;
import com.edoras.backend.domain.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Builder
@Setter
@Getter
@AllArgsConstructor
public class UserDetailsResponseDTO {
    private String name;

    private String userName;

    private String email;

    private Set<Role> roles;

    private List<Todo> todoList;
}
