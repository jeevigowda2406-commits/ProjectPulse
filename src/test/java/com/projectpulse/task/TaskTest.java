package com.projectpulse.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    @Test
    void newTaskShouldStartWithTodoStatus() {

        Task task = new Task(
                "TASK-001",
                "Design database schema",
                "Create the initial database design",
                8,
                TaskPriority.HIGH,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 12)
        );

        assertEquals(TaskStatus.TODO, task.getStatus());
    }

    @Test
    void taskShouldStorePriorityCorrectly() {

        Task task = new Task(
                "TASK-002",
                "Implement login",
                "Create secure user login",
                12,
                TaskPriority.CRITICAL,
                LocalDate.of(2026, 9, 13),
                LocalDate.of(2026, 9, 17)
        );

        assertEquals(TaskPriority.CRITICAL, task.getPriority());
    }

    @Test
    void taskShouldStoreEstimatedHoursCorrectly() {

        Task task = new Task(
                "TASK-003",
                "Create dashboard",
                "Build the project dashboard",
                20,
                TaskPriority.MEDIUM,
                LocalDate.of(2026, 9, 18),
                LocalDate.of(2026, 9, 22)
        );

        assertEquals(20, task.getEstimatedHours());
    }
}
