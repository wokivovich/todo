package com.todo.core.service;

import com.todo.core.entity.Task;
import com.todo.core.entity.User;
import com.todo.core.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(User user) {
        userRepository.save(user);
    }

    public Set<Task> getUncompletedTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        return user.getTasks().stream()
                .filter(task -> task.isDone() == false)
                .collect(Collectors.toSet());
    }

    @Transactional
    public void updateUserTasks(Task task, Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        user.getTasks().add(task);

        userRepository.save(user);
    }
}
