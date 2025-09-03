package com.example.Pulse.controller;

import com.example.Pulse.model.WorkProjectModel;
import com.example.Pulse.service.WorkProjectService;
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
        return workProjectService.createProject(project);
    }

    @GetMapping("/projects")
    public List<WorkProjectModel> getAllProjects(){
        return workProjectService.getAllProjects();
    }

    @GetMapping("/project/{id}")
    public WorkProjectModel getProject(@PathVariable String id){
        return workProjectService.getProjectById(id);
    }

    @PutMapping("/projects/{id}")
    public WorkProjectModel updateProject(@PathVariable String id, @RequestBody WorkProjectModel project) {
        return workProjectService.updateProject(id, project);
    }


    @DeleteMapping("/projects/{id}")
    public String deleteProject(@PathVariable String id) {
        return workProjectService.deleteProject(id);
    }


    @GetMapping("/projects/manager/{managerId}")
    public List<WorkProjectModel> getProjectsByManager(@PathVariable String managerId) {
        return workProjectService.getProjectsByManagerId(managerId);
    }
}


