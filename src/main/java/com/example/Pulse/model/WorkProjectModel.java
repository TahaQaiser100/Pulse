package com.example.Pulse.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "Work_project")
public class WorkProjectModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column (nullable = false)
    @NotBlank
    private String name;

    private LocalDateTime createdDate;

    @Column (nullable = true)
    private LocalDateTime endDate;

    @NotBlank
    private String managerId;

    @OneToMany (mappedBy = "project", cascade = CascadeType.ALL) // One project can have many tasks i.e. one-to-many relationship
    private List<WorkSessionModel> workSessions;

    public WorkProjectModel(){}

    public List<WorkSessionModel> getWorkSessions() {
        return workSessions;
    }

    public void setWorkSessions(List<WorkSessionModel> workSessions) {
        this.workSessions = workSessions;
    }

    public WorkProjectModel(String name, LocalDateTime createdDate, String managerId ){
        this.name = name;
        this.createdDate = createdDate;
        this.managerId = managerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }
}
