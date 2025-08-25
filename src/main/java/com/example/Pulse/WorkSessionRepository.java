package com.example.Pulse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSessionRepository extends JpaRepository<WorkSessionModel, String>{
    List<WorkSessionModel> findByUserId(String userId); // Finds all sessions for a specific person

    List<WorkSessionModel> findByProjectName(String projectName); // Finds all sessions for specific project

    List<WorkSessionModel> findByUserIdAndEndTimeIsNull(String userId); // Finds all active sessions by a specific person

    List<WorkSessionModel> findByUserIdAndEndTimeIsNotNull(String userId); // Finds all completed sessions by a specific person

    List<WorkSessionModel> findByProjectNameAndEndTimeIsNull(String projectName); // Finds all active sessions by project name

    List<WorkSessionModel> findByProjectNameAndEndTimeIsNotNull(String projectName); // Finds all completed sessions by project name
}
