package com.projectpulse.dependency;

public class TaskDependency {

    private final String taskId;
    private final String dependsOnTaskId;

    public TaskDependency(
            String taskId,
            String dependsOnTaskId
    ) {
        this.taskId = taskId;
        this.dependsOnTaskId = dependsOnTaskId;
    }

    public String getTaskId() {
        return taskId;
    }

    public String getDependsOnTaskId() {
        return dependsOnTaskId;
    }
}