package com.projectpulse.graph;

import com.projectpulse.dependency.TaskDependency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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

    public boolean hasCircularDependency() {

        Map<String, List<String>> graph = buildAdjacencyList();

        Set<String> visited = new HashSet<>();
        Set<String> currentPath = new HashSet<>();

        for (String taskId : graph.keySet()) {

            if (hasCycleFromTask(
                    taskId,
                    graph,
                    visited,
                    currentPath
            )) {
                return true;
            }
        }

        return false;
    }

    private Map<String, List<String>> buildAdjacencyList() {

        Map<String, List<String>> graph = new HashMap<>();

        for (TaskDependency dependency : dependencies) {

            String prerequisite = dependency.getDependsOnTaskId();
            String dependent = dependency.getTaskId();

            graph
                    .computeIfAbsent(prerequisite, key -> new ArrayList<>())
                    .add(dependent);

            graph.putIfAbsent(dependent, new ArrayList<>());
        }

        return graph;
    }

    private boolean hasCycleFromTask(
            String taskId,
            Map<String, List<String>> graph,
            Set<String> visited,
            Set<String> currentPath
    ) {

        if (currentPath.contains(taskId)) {
            return true;
        }

        if (visited.contains(taskId)) {
            return false;
        }

        visited.add(taskId);
        currentPath.add(taskId);

        for (String nextTask : graph.getOrDefault(
                taskId,
                List.of()
        )) {

            if (hasCycleFromTask(
                    nextTask,
                    graph,
                    visited,
                    currentPath
            )) {
                return true;
            }
        }

        currentPath.remove(taskId);

        return false;
    }
}