package com.edoras.backend.service;

import com.edoras.backend.domain.Role;
import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.User;
import com.edoras.backend.domain.dto.SignUpRequestDTO;
import com.edoras.backend.exception.ResourceNotFoundException;
import com.edoras.backend.mapper.UserMapper;
import com.edoras.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private TodoService todoService;

    private final RoleService roleService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Autowired
    public void setTodoService(TodoService todoService) {
        this.todoService = todoService;
    }

    public User registerUser(SignUpRequestDTO signUpRequestDTO) {
        Set<Role.RoleName> roles = signUpRequestDTO.getRoles().stream()
                .map(Role::getRoleName)
                .collect(Collectors.toSet());

        Set<Role> userRoles = roleService.getAllRolesByRoleNameIn(roles);

        User userToSave = UserMapper.convertSignUpRequestDtoToUser(signUpRequestDTO, passwordEncoder, userRoles);

        return userRepository.save(userToSave);
    }

    public User getUserDetails(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user for username = " + userName));
    }

    public List<Todo> getTodoListForUser(String userName) {
        return todoService.getAllTodosByUserName(userName);
    }

    public Todo getTodoForUserById(Long todoId) {
        return todoService.getTodo(todoId);
    }

    public void deleteUser(String userName) {
        User userToDelete = userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user for username = " + userName));
        userRepository.delete(userToDelete);
    }

    public User getByUserName(String userName) {
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user for username = " + userName));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Not found user for ID = " + userId));
    }

    public boolean existsByUserNameAndEmail(String userName, String email) {
        return userRepository.existsByUserNameAndEmail(userName, email);
    }

    public boolean existsByUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void updateUserPassword(String userName, String newPassword) {
        userRepository.updateUserByNewPassword(userName, passwordEncoder.encode(newPassword));
    }
}
