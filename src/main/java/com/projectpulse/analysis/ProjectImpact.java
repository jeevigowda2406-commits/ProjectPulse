package com.projectpulse.analysis;

public class ProjectImpact {

    private final String sourceTaskId;
    private final int affectedTaskCount;
    private final int affectedEstimatedHours;

    public ProjectImpact(
            String sourceTaskId,
            int affectedTaskCount,
            int affectedEstimatedHours
    ) {
        this.sourceTaskId = sourceTaskId;
        this.affectedTaskCount = affectedTaskCount;
        this.affectedEstimatedHours = affectedEstimatedHours;
    }

    public String getSourceTaskId() {
        return sourceTaskId;
    }

    public int getAffectedTaskCount() {
        return affectedTaskCount;
    }

    public int getAffectedEstimatedHours() {
        return affectedEstimatedHours;
    }
}