package com.edoras.backend.controller;

import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.ApiResponseDTO;
import com.edoras.backend.domain.dto.UserDetailsResponseDTO;
import com.edoras.backend.domain.dto.UserTodoResponseDTO;
import com.edoras.backend.mapper.TodoMapper;
import com.edoras.backend.mapper.UserMapper;
import com.edoras.backend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<UserDetailsResponseDTO> getUserDetails(@PathVariable String userName) {
        User user = userService.getUserDetails(userName);
        return ResponseEntity.ok(UserMapper.convertUserToUserDetailsResponseDto(user));
    }

    @GetMapping("/{userName}/todos")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<UserTodoResponseDTO>> getTodoListForUser(@PathVariable String userName) {
        List<Todo> todoList = userService.getTodoListForUser(userName);
        List<UserTodoResponseDTO> todoResponseDTOList =
                todoList.stream()
                .map(todo -> {
                    User createdByUser = userService.getUserById(todo.getCreatedBy());
                    return TodoMapper.convertTodoToUserTodoResponseDto(todo, createdByUser.getName());
                }).collect(Collectors.toList());
        return ResponseEntity.ok(todoResponseDTOList);
    }

    @GetMapping("/{userName}/todos/{todoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserTodoResponseDTO> getTodoForUserById(@PathVariable Long todoId) {
        Todo userTodo = userService.getTodoForUserById(todoId);
        User createdByUser = userService.getUserById(userTodo.getId());
        return ResponseEntity.ok(TodoMapper.convertTodoToUserTodoResponseDto(userTodo, createdByUser.getName()));
    }

    @DeleteMapping("/{userName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO> deleteUser(@PathVariable String userName) {
        userService.deleteUser(userName);
        return ResponseEntity.ok(new ApiResponseDTO(true, "Username: " + userName + " deleted successfully!"));
    }
}
