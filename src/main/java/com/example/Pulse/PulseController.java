package com.example.Pulse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController // Converts Java Objects to JSON
public class PulseController {

    @Autowired // Creates instance of workSessionRepository
    private WorkSessionRepository workSessionRepository; // Handles all db stuff, e.g. save, find etc

    @Autowired
    private WorkSessionService workSessionService;


    @GetMapping("/sessions") // Returns all sessions
    public List<WorkSessionModel> getAllSessions(){
        try{
            return workSessionService.getAllSessions();
        }catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create-session") // Creates a new session
    public WorkSessionModel createSession(@RequestBody WorkSessionModel session){ // Creates a WorkSessionModel object called session
        try {
            return workSessionService.startSession(session);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @PutMapping("/edit-session/{id}") // Update information about a session
    public WorkSessionModel updateSession(@PathVariable String id, @RequestBody WorkSessionModel session){
        try{
            return workSessionService.editSession(session, id);
        } catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }

    @DeleteMapping("/delete-session/{id}") // Delete session by id
    public String deleteSession(@PathVariable String id) {
        try{
            return workSessionService.deleteSession(id);
        } catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
