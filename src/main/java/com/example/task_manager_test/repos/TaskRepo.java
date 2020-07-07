package com.example.task_manager_test.repos;

import com.example.task_manager_test.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface TaskRepo extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
}
