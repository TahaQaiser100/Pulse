package com.example.Pulse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkProjectRepository extends JpaRepository<WorkProjectModel, String> {
    List<WorkProjectModel> findByManagerId(String ManagerId); // Finds all sessions for a specific person

    List<WorkProjectModel> findByName(String Name); // Finds all sessions for specific project

    List<WorkProjectModel> findByUserIdAndEndTimeIsNull(String userId); // Finds all active sessions by a specific person

    List<WorkProjectModel> findByEndSessionNotNull();

    List<WorkProjectModel> findByEndSessionIsNull();


}
