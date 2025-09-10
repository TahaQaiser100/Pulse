package com.example.Pulse.service;

import com.example.Pulse.model.WorkSessionModel;
import com.example.Pulse.repository.WorkSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkSessionService {

    @Autowired
    private WorkSessionRepository workSessionRepository;

    public List<WorkSessionModel> getAllSessions(){
        return workSessionRepository.findAll();
    }

    public WorkSessionModel startSession(WorkSessionModel session) { // Business logic for "/create-session"

        if(session.getUserId() == null || session.getUserId().trim().isEmpty()){
            throw new IllegalArgumentException("User ID is required");
        }

        if(session.getTaskName() == null || session.getTaskName().trim().isEmpty()){
            throw new IllegalArgumentException("Task name is required");
        }

        if(session.getProject() == null){
            throw new IllegalArgumentException("Project Name is required");
        }

        session.setId(UUID.randomUUID().toString());
        session.setStartTime(LocalDateTime.now());

        return workSessionRepository.save(session);
    }

    public WorkSessionModel editSession(WorkSessionModel session, String id){
        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Declare a variable called existingSession which is the session by id
        // Throw a 404 error if session by id is not found

        Integer newFocusScore = session.getFocusScore();
        Float newCompletedPercentage = session.getCompletedPercentage();
        String newNotes = session.getNotes();

        if(newFocusScore == null){
            throw new IllegalArgumentException("Please enter a valid number");
        }
        if(newFocusScore < 1 || newFocusScore > 10){
            throw new IllegalArgumentException("Focus Score must be between 1-10");
        }

        if(newCompletedPercentage == null){
            throw new IllegalArgumentException("Please enter a valid number");
        }
        if(newCompletedPercentage < 0 || newCompletedPercentage > 100){
            throw new IllegalArgumentException("Focus Score must be between 0-100");
        }

        if(newNotes != null && newNotes.length() > 400){
            throw new IllegalArgumentException("Exceeded length limit");
        }





        existingSession.setEndTime(LocalDateTime.now());
        existingSession.setFocusScore(session.getFocusScore());
        existingSession.setCompletedPercentage(session.getCompletedPercentage());
        existingSession.setNotes(session.getNotes());

        return workSessionRepository.save(existingSession);
    }


    public String deleteSession(String id){
        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        workSessionRepository.delete(existingSession);

        return "Session deleted";
    }
}
