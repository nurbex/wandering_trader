package com.wandering.trader.controllers;

import com.wandering.trader.domain.Project;
import com.wandering.trader.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    ProjectService projectService;
    @GetMapping
    public String getHome(Model model){
        model.addAttribute("projects", projectService.getAllProjects());
        return"home";
    }

    @GetMapping("/project/details")
    public String getProjectDetails(@RequestParam Long id, Model model){

        Optional<Project> projectOptional = projectService.getProjectByID(id);
        if (!projectOptional.isPresent()) {
            System.out.println("project not found");
            return "redirect:/admin/projects";
        }
        model.addAttribute("project", projectOptional.get());
        return"project_details";
    }
}
