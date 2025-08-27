package com.example.Pulse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController // Converts Java Objects to JSON
public class PulseController {

    @Autowired // Creates instance of workSessionRepository
    private WorkSessionRepository workSessionRepository; // Handles all db stuff, e.g. save, find etc


    @GetMapping("/sessions") // Returns all sessions
    public List<WorkSessionModel> getAllSessions(){
        return workSessionRepository.findAll();
    }

    @PostMapping("/create-session") // Creates a new session
    public WorkSessionModel createSession(@RequestBody WorkSessionModel session){ // Creates a WorkSessionModel object called session
        session.setId(UUID.randomUUID().toString());
        session.setStartTime(LocalDateTime.now());

        return workSessionRepository.save(session);
    }

    @PutMapping("/edit-session/{id}") // Update information about a session
    public WorkSessionModel updateSession(@PathVariable String id, @RequestBody WorkSessionModel session){

        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // Declare a variable called existingSession which is the session by id
        // Throw a 404 error if session by id is not found

        existingSession.setEndTime(LocalDateTime.now());
        existingSession.setFocusScore(session.getFocusScore());
        existingSession.setCompletedPercentage(session.getCompletedPercentage());
        existingSession.setNotes(session.getNotes());

        return workSessionRepository.save(existingSession);
    }

    @DeleteMapping("/delete-session/{id}") // Delete session by id
    public String deleteSession(@PathVariable String id){
        WorkSessionModel existingSession = workSessionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        workSessionRepository.delete(existingSession);

        return "Session deleted";
    }



}
