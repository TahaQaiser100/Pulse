package com.example.Pulse.service;


import com.example.Pulse.model.WorkProjectModel;
import com.example.Pulse.repository.WorkProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class WorkProjectService {

    @Autowired
    private WorkProjectRepository workProjectRepository;

    public WorkProjectModel createProject(WorkProjectModel project){
        if(project.getName() == null || project.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Project name is required");
        }

        if(project.getManagerId() == null || project.getManagerId().trim().isEmpty()){
            throw new IllegalArgumentException("Manager ID is required");
        }

        project.setId(UUID.randomUUID().toString());
        project.setCreatedDate(LocalDateTime.now());

        return workProjectRepository.save(project);
    }



    public List<WorkProjectModel> getAllProjects(){
        return workProjectRepository.findAll();
    }


    public WorkProjectModel getProjectById(String id){
        return workProjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<WorkProjectModel> getProjectsByManagerId(String managerId){
        return workProjectRepository.findByManagerId(managerId);
    }

    public WorkProjectModel updateProject(String id, WorkProjectModel project){
        WorkProjectModel existingProject = workProjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(project.getName() == null || project.getName().trim().isEmpty()){
            throw new IllegalArgumentException("Please enter a valid name");
        }

        existingProject.setName(project.getName().trim());

        return workProjectRepository.save(existingProject);
    }

    public String deleteProject(String id){
        WorkProjectModel existingProject = workProjectRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        workProjectRepository.delete(existingProject);
        return "Project Deleted Successfully";
    }
}
