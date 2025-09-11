package com.example.Pulse.controller;


import com.example.Pulse.model.WorkSessionModel;
import com.example.Pulse.repository.WorkSessionRepository;
import com.example.Pulse.service.WorkSessionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // Converts Java Objects to JSON
public class PulseController {

     // Creates instance of workSessionRepository

    private final WorkSessionService workSessionService;

    public PulseController(WorkSessionService workSessionService) {
        this.workSessionService = workSessionService;
    }

    @GetMapping("/sessions") // Returns all sessions
    public List<WorkSessionModel> getAllSessions(){
        return workSessionService.getAllSessions();
    }

    @PostMapping("/create-session") // Creates a new session
    public WorkSessionModel createSession(@Valid @RequestBody WorkSessionModel session){ // Creates a WorkSessionModel object called session
        return workSessionService.startSession(session);
    }

    @PutMapping("/edit-session/{id}") // Update information about a session
    public WorkSessionModel updateSession(@PathVariable String id, @Valid @RequestBody WorkSessionModel session){
        return workSessionService.editSession(session, id);
    }

    @DeleteMapping("/delete-session/{id}") // Delete session by id
    public String deleteSession(@PathVariable String id) {
        return workSessionService.deleteSession(id);
    }

}
