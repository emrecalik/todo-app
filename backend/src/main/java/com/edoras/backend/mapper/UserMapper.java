package com.edoras.backend.mapper;

import com.edoras.backend.domain.Role;
import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.SignUpRequestDTO;
import com.edoras.backend.domain.dto.SignUpResponseDTO;
import com.edoras.backend.domain.dto.UserDetailsResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class UserMapper {

    public static User convertSignUpRequestDtoToUser(SignUpRequestDTO signUpRequestDTO,
                                                     PasswordEncoder passwordEncoder, Set<Role> userRoles) {
        return User.builder()
                .name(signUpRequestDTO.getName())
                .userName(signUpRequestDTO.getUserName())
                .email(signUpRequestDTO.getEmail())
                .password(passwordEncoder.encode(signUpRequestDTO.getPassword()))
                .roles(userRoles)
                .build();
    }

    public static SignUpResponseDTO convertUserToSignUpResponseDto(User user) {
        return SignUpResponseDTO.builder()
                .successful(true)
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .build();
    }

    public static UserDetailsResponseDTO convertUserToUserDetailsResponseDto(User user) {
        return UserDetailsResponseDTO.builder()
                .name(user.getName())
                .userName(user.getUserName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }

}
