package com.edoras.backend.mapper;

import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.TodoRequestDTO;
import com.edoras.backend.domain.dto.TodoResponseDTO;
import com.edoras.backend.domain.dto.UserTodoResponseDTO;

import java.time.Duration;
import java.time.Instant;

public class TodoMapper {

    public static UserTodoResponseDTO convertTodoToUserTodoResponseDto(Todo todo, String createdByName) {
        return UserTodoResponseDTO.builder()
                .id(todo.getId())
                .description(todo.getDescription())
                .createdBy(createdByName)
                .isExpired(todo.getExpiredAt().isBefore(Instant.now()))
                .expiredAt(todo.getExpiredAt())
                .build();
    }

    public static Todo convertTodoRequestDtoToUser(Todo todo, User todoUser, TodoRequestDTO todoRequestDTO) {
        todo.setDescription(todoRequestDTO.getDescription());
        todo.setUser(todoUser);
        todo.setExpiredAt(Instant.now()
                .plus(Duration.ofDays(todoRequestDTO.getTodoDays())
                .plus(Duration.ofHours(todoRequestDTO.getTodoHours()))));
        return todo;
    }

    public static TodoResponseDTO convertTodoToTodoResponseDTO(Todo todo, String createdByName, String updatedByName) {
        return TodoResponseDTO.builder()
                .id(todo.getId())
                .createdById(todo.getCreatedBy())
                .description(todo.getDescription())
                .createdByName(createdByName)
                .createdAt(todo.getCreatedAt().toEpochMilli())
                .updatedByName(updatedByName)
                .updatedAt(todo.getUpdatedAt().toEpochMilli())
                .responsible(todo.getUser().getName())
                .expiredAt(todo.getExpiredAt().toEpochMilli())
                .build();
    }
}
