package com.edoras.backend.service;

import com.edoras.backend.domain.Role;
import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.TodoRequestDTO;
import com.edoras.backend.exception.BadRequestException;
import com.edoras.backend.exception.ResourceNotFoundException;
import com.edoras.backend.mapper.TodoMapper;
import com.edoras.backend.repository.TodoRepository;
import com.edoras.backend.security.MyUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final TodoRepository todoRepository;

    private final UserService userService;

    public TodoService(TodoRepository todoRepository, UserService userService) {
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    public Todo saveTodo(TodoRequestDTO todoRequestDTO) {
        authorityCheck(todoRequestDTO);

        User todoUser = userService.getByUserName(todoRequestDTO.getUserName());

        Todo todoToSave = TodoMapper.convertTodoRequestDtoToUser(new Todo(), todoUser, todoRequestDTO);

        return todoRepository.save(todoToSave);
    }

    public Todo updateTodo(Long todoId, TodoRequestDTO todoRequestDTO) {
        authorityCheck(todoRequestDTO);

        Todo todoToUpdate = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found todo for ID = " + todoId));

        User todoUser = userService.getByUserName(todoRequestDTO.getUserName());

        Todo todoUpdated = TodoMapper.convertTodoRequestDtoToUser(todoToUpdate, todoUser, todoRequestDTO);
        return todoRepository.save(todoUpdated);
    }

    private void authorityCheck(TodoRequestDTO todoRequestDTO) {
        MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!myUserDetails.getUsername().equals(todoRequestDTO.getUserName()) &&
                !myUserDetails.getAuthorities().toString().contains(Role.RoleName.ROLE_ADMIN.toString())) {
            throw new BadRequestException("Current user: " + myUserDetails.getUsername()
                    + " has no authority to assign a Todo for another user: " + todoRequestDTO.getUserName());
        }
    }

    public Todo getTodo(Long todoId) {
        return todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Todo for ID = " + todoId));
    }

    public void deleteTodo(Long todoId) {
        Todo todoToDelete = todoRepository.findById(todoId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Todo for ID = " + todoId));
        todoRepository.delete(todoToDelete);
    }

    public List<Todo> getAllTodosByUserName(String userName) {
        return todoRepository.findAllByUser(userService.getByUserName(userName));
    }

    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    public String getCreatedByName(Todo todo) {
        return userService.getUserById(todo.getCreatedBy()).getName();
    }

    public String getUpdatedByName(Todo todo) {
        return userService.getUserById(todo.getUpdatedBy()).getName();
    }
}
