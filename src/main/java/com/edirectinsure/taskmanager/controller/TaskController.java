package com.edirectinsure.taskmanager.controller;

import com.edirectinsure.taskmanager.model.Task;
import com.edirectinsure.taskmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/tasks")
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public Task create(@RequestBody Task task) {
        return taskService.create(task);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT, produces = "application/json")
    public Task update(@RequestBody Task task) {
        return taskService.update(task);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        taskService.deleteById(id);
    }
}
