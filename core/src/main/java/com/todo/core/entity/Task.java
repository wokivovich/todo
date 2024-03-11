package com.todo.core.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "task")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "done")
    private boolean isDone;

}
