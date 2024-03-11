package com.todo.core.api;

import com.todo.core.entity.Task;
import com.todo.core.entity.User;
import com.todo.core.service.TaskService;
import com.todo.core.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(
        path = "/api",
        produces = "application/json"
)
public class Controller {

    private final TaskService taskService;
    private final UserService userService;

    public Controller(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @PostMapping("/{author_id}/task/new")
    public ResponseEntity<String> createTask(@PathVariable("author_id") Long authorId, @RequestBody String taskDescription) {
        Task task = Task.builder()
                .description(taskDescription)
                .isDone(false)
                .build();
        taskService.createTask(task);
        userService.updateUserTasks(task, authorId);

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PostMapping("/user/new")
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(User.builder()
                .name(userRequest.name)
                .email(userRequest.email)
                .password(userRequest.password)
                .build());

        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("/user/{user_id}/tasks")
    public ResponseEntity<Set<Task>> userActiveTasks(@PathVariable("user_id") Long userId) {
        Set<Task> userTasks = userService.getUncompletedTasks(userId);

        return new ResponseEntity<>(userTasks, HttpStatus.FOUND);
    }

    @PatchMapping("/task/complete/{task_id}")
    public ResponseEntity<String> completeTask(@PathVariable("task_id") Long taskId) {
        taskService.completeTask(taskId);

        return new ResponseEntity<>("Updated", HttpStatus.OK);
    }

    record UserRequest(String name, String email, String password) {}

}
