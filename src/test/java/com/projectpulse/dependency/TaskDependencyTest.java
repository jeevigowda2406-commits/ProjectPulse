package com.projectpulse.dependency;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskDependencyTest {

    @Test
    void dependencyShouldStoreTaskIdsCorrectly() {

        TaskDependency dependency = new TaskDependency(
                "TASK-002",
                "TASK-001"
        );

        assertEquals("TASK-002", dependency.getTaskId());
        assertEquals("TASK-001", dependency.getDependsOnTaskId());
    }
}