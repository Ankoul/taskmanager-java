package com.edirectinsure.taskmanager.service;

import com.edirectinsure.taskmanager.model.Project;
import com.edirectinsure.taskmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> listAll(){
        return projectRepository.findAll();
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        return projectRepository.save(project);
    }

    public Project findById(Long projectId) {
        return projectRepository.findById(projectId).get();
    }

    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
