package com.edirectinsure.taskmanager.service;

import com.edirectinsure.taskmanager.exception.BadRequestException;
import com.edirectinsure.taskmanager.exception.ProjectNotFoundException;
import com.edirectinsure.taskmanager.exception.TaskNotFoundException;
import com.edirectinsure.taskmanager.model.Task;
import com.edirectinsure.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private ProjectService projectService;

    @Autowired
    public TaskService(TaskRepository taskRepository, ProjectService projectService) {
        this.taskRepository = taskRepository;
        this.projectService = projectService;
    }


    public Task create(Task task) {
        if (!projectService.existsById(task.getProjectId())) {
            throw new ProjectNotFoundException(task.getProjectId());
        }
		task.setCreateDate(new Date());
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        if (!projectService.existsById(task.getProjectId())) {
            throw new ProjectNotFoundException(task.getProjectId());
        }
        Optional<Task> optionalTask = taskRepository.findById(task.getId());
        Task storedTask = optionalTask.orElseThrow(new TaskNotFoundException(task.getId()));
        if (storedTask.getFinishDate() != null && task.getFinishDate() != null) {
            throw new BadRequestException("This task had already finished. Reopen to update.");
        }
        return taskRepository.save(task);
    }

    public void deleteById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        Task storedTask = optionalTask.orElseThrow(new TaskNotFoundException(id));
        projectService.checkProjectVisibility(storedTask.getProjectId());

        if (storedTask.getFinishDate() != null) {
            throw new BadRequestException("This task had already finished. Reopen to delete.");
        }
        taskRepository.deleteById(id);
    }


}
