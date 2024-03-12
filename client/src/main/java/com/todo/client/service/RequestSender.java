package com.todo.client.service;

import com.todo.client.Task;
import com.todo.client.request.TaskRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "core", url = "localhost:5000")
public interface RequestSender {

    @PostMapping("/api/{user_id}/task/new")
    void createTask(@PathVariable("user_id") Long userId, @RequestBody TaskRequest taskRequest);

    @GetMapping("/api/user/{user_id}/tasks")
    List<Task> getUserActiveTasks(@PathVariable("user_id") Long userId);

    @PostMapping("/api/task/complete/{task_id}")
    void completeTask(@PathVariable("task_id") Long taskId);
}
