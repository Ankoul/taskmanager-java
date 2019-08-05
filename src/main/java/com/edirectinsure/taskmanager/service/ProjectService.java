package com.edirectinsure.taskmanager.service;

import com.edirectinsure.taskmanager.exception.ProjectNotFoundException;
import com.edirectinsure.taskmanager.model.Project;
import com.edirectinsure.taskmanager.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"WeakerAccess", "BooleanMethodIsAlwaysInverted"})
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
        if (!this.existsById(project.getId())) {
            throw new ProjectNotFoundException(project.getId());
        }
        return projectRepository.save(project);
    }

    public boolean existsById(Long projectId) {
        return projectRepository.existsById(projectId);
    }

    public void delete(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
