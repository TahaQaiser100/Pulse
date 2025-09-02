package com.example.Pulse;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity // Create a database table
@Table (name = "Work_Session")
public class WorkSessionModel {
    @Id
    private String id;
    private String userId;

    @ManyToOne
    @JoinColumn (name= "project_id")
    private WorkProjectModel project;

    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer focusScore;
    private float completedPercentage;
    private String notes;


    public WorkSessionModel(){}

    public WorkSessionModel(String userId, WorkProjectModel project, String taskName){
        this.userId = userId; // Who is working
        this.taskName = taskName; // What task is the user working on
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public WorkProjectModel getProject() {
        return project;
    }

    public void setProject(WorkProjectModel project) {
        this.project = project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getFocusScore() {
        return focusScore;
    }

    public void setFocusScore(Integer focusScore) {
        this.focusScore = focusScore;
    }

    public float getCompletedPercentage() {
        return completedPercentage;
    }

    public void setCompletedPercentage(float completedPercentage) {
        this.completedPercentage = completedPercentage;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
