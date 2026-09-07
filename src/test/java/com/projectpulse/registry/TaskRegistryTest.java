package com.projectpulse.registry;

import com.projectpulse.task.Task;
import com.projectpulse.task.TaskPriority;
import com.projectpulse.task.TaskStatus;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskRegistryTest {

    @Test
    void registryShouldStoreAndFindTaskById() {

        TaskRegistry registry = new TaskRegistry();

        Task task = new Task(
                "TASK-001",
                "Design database schema",
                "Create the initial database design",
                8,
                TaskPriority.HIGH,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 12)
        );

        registry.addTask(task);

        Optional<Task> result =
                registry.findTaskById("TASK-001");

        assertTrue(result.isPresent());
        assertEquals(task, result.get());
    }

    @Test
    void registryShouldTrackNumberOfTasks() {

        TaskRegistry registry = new TaskRegistry();

        Task task1 = new Task(
                "TASK-001",
                "Design database",
                "Create database design",
                8,
                TaskPriority.HIGH,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 12)
        );

        Task task2 = new Task(
                "TASK-002",
                "Build backend",
                "Implement backend services",
                20,
                TaskPriority.CRITICAL,
                LocalDate.of(2026, 9, 13),
                LocalDate.of(2026, 9, 20)
        );

        registry.addTask(task1);
        registry.addTask(task2);

        assertEquals(2, registry.size());
    }

    @Test
    void registryShouldReturnEmptyWhenTaskDoesNotExist() {

        TaskRegistry registry = new TaskRegistry();

        Optional<Task> result =
                registry.findTaskById("TASK-999");

        assertTrue(result.isEmpty());
    }
}