package com.projectpulse.analysis;

import com.projectpulse.dependency.TaskDependency;
import com.projectpulse.graph.DependencyGraph;
import com.projectpulse.registry.TaskRegistry;
import com.projectpulse.task.Task;
import com.projectpulse.task.TaskPriority;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectAnalyzerTest {

    @Test
    void analyzerShouldCalculateAffectedTaskCountAndEffort() {

        TaskRegistry registry = new TaskRegistry();

        Task databaseTask = new Task(
                "TASK-001",
                "Design database",
                "Create database design",
                8,
                TaskPriority.HIGH,
                LocalDate.of(2026, 9, 10),
                LocalDate.of(2026, 9, 12)
        );

        Task backendTask = new Task(
                "TASK-002",
                "Build backend",
                "Implement backend services",
                20,
                TaskPriority.CRITICAL,
                LocalDate.of(2026, 9, 13),
                LocalDate.of(2026, 9, 20)
        );

        Task apiTask = new Task(
                "TASK-003",
                "Build API",
                "Create REST API",
                12,
                TaskPriority.HIGH,
                LocalDate.of(2026, 9, 21),
                LocalDate.of(2026, 9, 25)
        );

        registry.addTask(databaseTask);
        registry.addTask(backendTask);
        registry.addTask(apiTask);

        DependencyGraph graph = new DependencyGraph();

        graph.addDependency(
                new TaskDependency("TASK-002", "TASK-001")
        );

        graph.addDependency(
                new TaskDependency("TASK-003", "TASK-002")
        );

        ProjectAnalyzer analyzer =
                new ProjectAnalyzer(registry, graph);

        ProjectImpact impact =
                analyzer.analyzeImpact("TASK-001");

        assertEquals("TASK-001", impact.getSourceTaskId());
        assertEquals(2, impact.getAffectedTaskCount());
        assertEquals(32, impact.getAffectedEstimatedHours());
    }
}