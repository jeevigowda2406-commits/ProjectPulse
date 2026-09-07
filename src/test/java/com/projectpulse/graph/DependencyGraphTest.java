package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;
import org.junit.jupiter.api.Test;

import java.util.Set;

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

    @Test
    void graphShouldFindDirectlyAffectedTasks() {

        DependencyGraph graph = new DependencyGraph();

        graph.addDependency(
                new TaskDependency("TASK-002", "TASK-001")
        );

        graph.addDependency(
                new TaskDependency("TASK-003", "TASK-001")
        );

        Set<String> affectedTasks =
                graph.findDirectlyAffectedTasks("TASK-001");

        assertEquals(2, affectedTasks.size());

        assertEquals(
                Set.of("TASK-002", "TASK-003"),
                affectedTasks
        );
    }

    @Test
    void graphShouldFindAllDownstreamAffectedTasks() {

        DependencyGraph graph = new DependencyGraph();

        graph.addDependency(
                new TaskDependency("TASK-002", "TASK-001")
        );

        graph.addDependency(
                new TaskDependency("TASK-003", "TASK-002")
        );

        graph.addDependency(
                new TaskDependency("TASK-004", "TASK-003")
        );

        Set<String> affectedTasks =
                graph.findAllAffectedTasks("TASK-001");

        assertEquals(
                Set.of("TASK-002", "TASK-003", "TASK-004"),
                affectedTasks
        );
    }

    @Test
    void graphShouldFindAllAffectedTasksInBranchingStructure() {

        DependencyGraph graph = new DependencyGraph();

        // TASK-002 depends on TASK-001
        graph.addDependency(
                new TaskDependency("TASK-002", "TASK-001")
        );

        // TASK-003 depends on TASK-001
        graph.addDependency(
                new TaskDependency("TASK-003", "TASK-001")
        );

        // TASK-004 depends on TASK-002
        graph.addDependency(
                new TaskDependency("TASK-004", "TASK-002")
        );

        // TASK-004 also depends on TASK-003
        graph.addDependency(
                new TaskDependency("TASK-004", "TASK-003")
        );

        Set<String> affectedTasks =
                graph.findAllAffectedTasks("TASK-001");

        assertEquals(
                Set.of("TASK-002", "TASK-003", "TASK-004"),
                affectedTasks
        );
    }
}