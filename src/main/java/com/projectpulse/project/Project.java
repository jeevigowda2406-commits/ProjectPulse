package com.projectpulse.project;

import java.time.LocalDate;

public class Project {

    private final String id;
    private String name;
    private String description;
    private LocalDate deadline;
    private ProjectStatus status;

    public Project(
            String id,
            String name,
            String description,
            LocalDate deadline
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.status = ProjectStatus.PLANNED;
    }
}
