package com.example.Pulse.repository;

import com.example.Pulse.model.WorkProjectModel;
import com.example.Pulse.model.WorkSessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkSessionRepository extends JpaRepository<WorkSessionModel, String>{
    List<WorkSessionModel> findByUserId(String userId); // Finds all sessions for a specific person

    List<WorkSessionModel> findByUserIdAndEndTimeIsNull(String userId); // Finds all active sessions by a specific person

    List<WorkSessionModel> findByUserIdAndEndTimeIsNotNull(String userId); // Finds all completed sessions by a specific person

}
