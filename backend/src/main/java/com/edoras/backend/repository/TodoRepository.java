package com.edoras.backend.repository;

import com.edoras.backend.domain.Todo;
import com.edoras.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findAllByUser(User user);
    Optional<Todo> findById(Long todoId);
}
