package com.edirectinsure.taskmanager.controller;

import com.edirectinsure.taskmanager.model.Project;
import com.edirectinsure.taskmanager.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/projects")
public class ProjectController {


    private ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public List<Project> listProjects(){
        return projectService.listAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Project create(@RequestBody Project project) {
        return projectService.create(project);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public Project update(@RequestBody Project project) {
        return projectService.update(project);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}
