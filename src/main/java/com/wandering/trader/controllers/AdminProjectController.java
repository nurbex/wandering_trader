package com.wandering.trader.controllers;

import com.wandering.trader.domain.Project;
import com.wandering.trader.services.ImageService;
import com.wandering.trader.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
@RequestMapping("/admin/projects")
public class AdminProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ImageService imageService;

    @GetMapping
    public String getProjects(Model model){
        model.addAttribute("projects", projectService.getAllProjects());
        return "project_list";
    }

    @GetMapping("/new")
    public String createNewProject(Model model){
        model.addAttribute("images", imageService.getImageList());
        model.addAttribute("project", new Project());
        return "new_project";
    }

    @PostMapping
    public String createProject(Project project){
        projectService.createOrUpdateProject(project);
        return "redirect:/admin/projects";
    }
    @GetMapping("/delete")
    public String deleteProject(@RequestParam Long id){

        projectService.deleteProject(id);
        return "redirect:/admin/projects";
    }

    @GetMapping("/edit")
    public String editProject(@RequestParam Long id, Model model){

        Optional<Project> projectOptional = projectService.getProjectByID(id);
        if (!projectOptional.isPresent()) {
            System.out.println("project not found");
            return "redirect:/admin/projects";
        }
        model.addAttribute("images", imageService.getImageList());
        model.addAttribute("project", projectOptional.get());
        return "edit_project";
    }

    @PostMapping("/edit")
    public String saveProject(Project project){

        projectService.createOrUpdateProject(project);
        return "redirect:/project/details?id="+project.getId();
    }
}

