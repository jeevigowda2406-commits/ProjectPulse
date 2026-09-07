package com.projectpulse.registry;

import com.projectpulse.task.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TaskRegistry {

    private final Map<String, Task> tasks;

    public TaskRegistry() {
        this.tasks = new HashMap<>();
    }

    public void addTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public Optional<Task> findTaskById(String taskId) {
        return Optional.ofNullable(tasks.get(taskId));
    }

    public int size() {
        return tasks.size();
    }
}