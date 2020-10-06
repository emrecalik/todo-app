package com.edoras.backend.controller;

import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.dto.ApiResponseDTO;
import com.edoras.backend.domain.dto.TodoRequestDTO;
import com.edoras.backend.domain.dto.TodoResponseDTO;
import com.edoras.backend.mapper.TodoMapper;
import com.edoras.backend.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo/{todoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
        public ResponseEntity<TodoResponseDTO> getTodoById(@PathVariable Long todoId) {
        Todo todo = todoService.getTodo(todoId);
        String createdByName = todoService.getCreatedByName(todo);
        String updatedByName = todoService.getUpdatedByName(todo);
        TodoResponseDTO todoResponseDTO = TodoMapper.convertTodoToTodoResponseDTO(todo, createdByName, updatedByName);
        return ResponseEntity.ok(todoResponseDTO);
    }

    @PostMapping("/todo")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Todo> saveTodo(@Valid @RequestBody TodoRequestDTO todoRequestDTO) {
        Todo todoSaved = todoService.saveTodo(todoRequestDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{todoId}")
                .buildAndExpand(todoSaved.getId()).toUri();

        return ResponseEntity.created(location).body(todoSaved);
    }

    @PutMapping("/todo/{todoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Todo> updateTodo(@PathVariable Long todoId, @Valid @RequestBody TodoRequestDTO todoRequestDTO) {
        return ResponseEntity.ok(todoService.updateTodo(todoId, todoRequestDTO));
    }

    @DeleteMapping("/todo/{todoId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponseDTO> deleteTodo(@PathVariable Long todoId) {
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok(new ApiResponseDTO(true, "Todo ID = " + todoId + " deleted"));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<TodoResponseDTO>> getAllTodos() {
        List<Todo> todos = todoService.getAllTodos();
        List<TodoResponseDTO> todoResponseDTOList = todos.stream().map((todo) -> {
            String createdByName = todoService.getCreatedByName(todo);
            String updatedByName = todoService.getUpdatedByName(todo);
            return TodoMapper.convertTodoToTodoResponseDTO(todo, createdByName, updatedByName);
        }).collect(Collectors.toList());
        return ResponseEntity.ok(todoResponseDTOList);
    }
}
