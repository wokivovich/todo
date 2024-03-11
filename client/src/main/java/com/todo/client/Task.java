package com.todo.client;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {

    private Long id;
    private String task;
    private boolean done;
}
