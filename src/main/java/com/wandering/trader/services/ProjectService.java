package com.wandering.trader.services;

import com.wandering.trader.domain.Project;
import com.wandering.trader.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    public void createOrUpdateProject(Project project){
        projectRepository.save(project);
    }
    public List<Project> getAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProject(Long id){
        projectRepository.deleteById(id);
    }

    public Optional<Project> getProjectByID(Long id){
        return projectRepository.findById(id);
    }
}
