package com.projectpulse.task;

import java.time.LocalDate;

public class Task {

    private final String id;
    private String title;
    private String description;
    private int estimatedHours;
    private TaskPriority priority;
    private TaskStatus status;
    private LocalDate startDate;
    private LocalDate dueDate;

    public Task(
            String id,
            String title,
            String description,
            int estimatedHours,
            TaskPriority priority,
            LocalDate startDate,
            LocalDate dueDate
    ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.estimatedHours = estimatedHours;
        this.priority = priority;
        this.status = TaskStatus.TODO;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }
    public String getId() {
        return id;
    }
    public TaskStatus getStatus() {
        return status;
    }
    public TaskPriority getPriority() {
        return priority;
    }

    public int getEstimatedHours() {
        return estimatedHours;
    }
}