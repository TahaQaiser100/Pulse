package com.example.Pulse;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity // Create a database table
@Table (name = "Work_Session")
public class WorkSessionModel {
    @Id
    private String id;
    private String userId;
    private String projectName;
    private String taskName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer focusScore;
    private float completedPercentage;
    private String notes;


    public WorkSessionModel(){}

    public WorkSessionModel(String userId, String projectName, String taskName){
        this.userId = userId; // Who is working
        this.projectName = projectName; // What project the user is working on
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
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
