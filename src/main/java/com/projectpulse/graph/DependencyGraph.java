package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedList;
import java.util.Queue;
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
    public Set<String> findAllAffectedTasks(String taskId) {

        Set<String> affectedTasks = new HashSet<>();
        Queue<String> tasksToProcess = new LinkedList<>();

        tasksToProcess.add(taskId);

        while (!tasksToProcess.isEmpty()) {

            String currentTask = tasksToProcess.poll();

            Set<String> directlyAffected =
                    findDirectlyAffectedTasks(currentTask);

            for (String affectedTask : directlyAffected) {

                if (affectedTasks.add(affectedTask)) {
                    tasksToProcess.add(affectedTask);
                }
            }
        }

        return affectedTasks;
    }
}