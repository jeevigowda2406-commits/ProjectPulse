package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class DependencyGraph {

    private final List<TaskDependency> dependencies;

    public DependencyGraph() {
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(TaskDependency dependency) {
        dependencies.add(dependency);
    }

    public List<TaskDependency> getDependencies() {
        return List.copyOf(dependencies);
    }
    public Set<String> findDirectlyAffectedTasks(String taskId) {

        Set<String> affectedTasks = new HashSet<>();

        for (TaskDependency dependency : dependencies) {

            if (dependency.getDependsOnTaskId().equals(taskId)) {
                affectedTasks.add(dependency.getTaskId());
            }
        }

        return affectedTasks;
    }
}