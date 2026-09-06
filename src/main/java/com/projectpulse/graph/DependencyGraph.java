package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;

import java.util.ArrayList;
import java.util.List;

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
}