package com.example.Pulse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ProjectController {

    @Autowired
    private WorkProjectService workProjectService;


    @PostMapping("/projects")
    public WorkProjectModel createProject(@RequestBody WorkProjectModel project){
        // @RequestBody  turns JSON into a Java Object
        // Client sends JSOn to server, and Spring creates WorkProjectModel project = new WorProjectModel()
        // converts JSON data to Java Object i.e. project.setName("Project111")
        try{
            return workProjectService.createProject(project);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/projects")
    public List<WorkProjectModel> getAllProjects(){
        try{
            return workProjectService.getAllProjects();
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }
    }
    @GetMapping("/project/{id}")
    public WorkProjectModel getProject(@PathVariable String id){
        try{
            return workProjectService.getProjectById(id);
        }catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());

        }

    }

    @PutMapping("/projects/{id}")
    public WorkProjectModel updateProject(@PathVariable String id, @RequestBody WorkProjectModel project) {
        try {
            return workProjectService.updateProject(id, project);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


    @DeleteMapping("/projects/{id}")
    public String deleteProject(@PathVariable String id) {
        try {
            return workProjectService.deleteProject(id);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


    @GetMapping("/projects/manager/{managerId}")
    public List<WorkProjectModel> getProjectsByManager(@PathVariable String managerId) {
        try {
            return workProjectService.getProjectsByManagerId(managerId);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}


