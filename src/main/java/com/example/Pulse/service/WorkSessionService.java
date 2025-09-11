package com.example.Pulse.service;

import com.example.Pulse.model.WorkSessionModel;
import com.example.Pulse.repository.WorkSessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkSessionService {
    private final WorkSessionRepository workSessionRepository;

    public WorkSessionService(WorkSessionRepository workSessionRepository) {
        this.workSessionRepository = workSessionRepository;
    }

    public List<WorkSessionModel> getAllSessions(){
        return workSessionRepository.findAll();
    }

    public WorkSessionModel startSession(WorkSessionModel session) {

        session.setId(UUID.randomUUID().toString());
        session.setStartTime(LocalDateTime.now());

        return workSessionRepository.save(session);
    }

    public WorkSessionModel editSession(WorkSessionModel session, String id){
        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found"));

        existingSession.setEndTime(LocalDateTime.now());
        existingSession.setFocusScore(session.getFocusScore());
        existingSession.setCompletedPercentage(session.getCompletedPercentage());
        existingSession.setNotes(session.getNotes());

        return workSessionRepository.save(existingSession);
    }

    public String deleteSession(String id){
        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Session not found"));

        workSessionRepository.delete(existingSession);

        return "Session deleted";
    }
}