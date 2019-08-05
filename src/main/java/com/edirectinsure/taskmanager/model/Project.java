package com.edirectinsure.taskmanager.model;


import javax.persistence.*;
import java.util.List;

@Entity
@SuppressWarnings("unused")
public class Project {

    @Id
    @GeneratedValue
    private Long Id;
    private String name;
    private Long userId;

    @OneToMany(orphanRemoval=true)
    @JoinColumn(name="project_id")
    private List<Task> tasks;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
