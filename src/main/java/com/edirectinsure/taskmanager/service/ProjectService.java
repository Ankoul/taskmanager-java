package com.edirectinsure.taskmanager.service;

import com.edirectinsure.taskmanager.exception.ForbiddenProjectException;
import com.edirectinsure.taskmanager.exception.ProjectNotFoundException;
import com.edirectinsure.taskmanager.model.Project;
import com.edirectinsure.taskmanager.model.User;
import com.edirectinsure.taskmanager.repository.ProjectRepository;
import com.edirectinsure.taskmanager.security.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@SuppressWarnings({"WeakerAccess", "BooleanMethodIsAlwaysInverted"})
@Service
public class ProjectService {

    private ProjectRepository projectRepository;
    private AuthenticationService authenticationService;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, AuthenticationService authenticationService) {
        this.projectRepository = projectRepository;
        this.authenticationService = authenticationService;
    }

    public List<Project> listAllByCurrentUser(){
        User user = authenticationService.getAuthenticatedUser();
        return projectRepository.findAllByUserId(user.getId());
    }

    public Project create(Project project) {
        User user = authenticationService.getAuthenticatedUser();
        project.setUserId(user.getId());
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        checkProjectVisibility(project.getId());
        return projectRepository.save(project);
    }

    public boolean existsById(Long projectId) {
        checkProjectVisibility(projectId);
        return projectRepository.existsById(projectId);
    }

    public void delete(Long projectId) {
        checkProjectVisibility(projectId);
        projectRepository.deleteById(projectId);
    }

    public void checkProjectVisibility(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(new ProjectNotFoundException(id));

        User user = authenticationService.getAuthenticatedUser();
        if (!project.getUserId().equals(user.getId())) {
            throw new ForbiddenProjectException();
        }
    }
}
