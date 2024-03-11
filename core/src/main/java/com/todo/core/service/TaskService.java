package com.todo.core.service;

import com.todo.core.entity.Task;
import com.todo.core.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        task.setDone(false);

        taskRepository.save(task);
    }

    @Transactional
    public void completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setDone(true);

        taskRepository.save(task);
    }
}
