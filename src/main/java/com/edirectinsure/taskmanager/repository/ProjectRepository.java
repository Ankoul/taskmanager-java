package com.edirectinsure.taskmanager.repository;

import com.edirectinsure.taskmanager.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    List<Project> findAllByUserId(Long userId);
}
