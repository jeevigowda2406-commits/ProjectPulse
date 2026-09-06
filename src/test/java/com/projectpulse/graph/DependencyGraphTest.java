package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DependencyGraphTest {

    @Test
    void graphShouldStoreDependencies() {

        DependencyGraph graph = new DependencyGraph();

        TaskDependency dependency = new TaskDependency(
                "TASK-002",
                "TASK-001"
        );

        graph.addDependency(dependency);

        assertEquals(1, graph.getDependencies().size());
        assertEquals(
                "TASK-002",
                graph.getDependencies().get(0).getTaskId()
        );
    }
}