package com.projectpulse.analysis;

import com.projectpulse.graph.DependencyGraph;
import com.projectpulse.registry.TaskRegistry;
import com.projectpulse.task.Task;

import java.util.Set;

public class ProjectAnalyzer {

    private final TaskRegistry taskRegistry;
    private final DependencyGraph dependencyGraph;

    public ProjectAnalyzer(
            TaskRegistry taskRegistry,
            DependencyGraph dependencyGraph
    ) {
        this.taskRegistry = taskRegistry;
        this.dependencyGraph = dependencyGraph;
    }

    public ProjectImpact analyzeImpact(String taskId) {

        Set<String> affectedTaskIds =
                dependencyGraph.findAllAffectedTasks(taskId);

        int affectedEstimatedHours = 0;

        for (String affectedTaskId : affectedTaskIds) {

            Task task = taskRegistry
                    .findTaskById(affectedTaskId)
                    .orElseThrow();

            affectedEstimatedHours += task.getEstimatedHours();
        }

        return new ProjectImpact(
                taskId,
                affectedTaskIds.size(),
                affectedEstimatedHours
        );
    }
}