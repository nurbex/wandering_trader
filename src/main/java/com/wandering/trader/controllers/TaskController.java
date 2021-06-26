package com.wandering.trader.controllers;

import com.wandering.trader.domain.CustomUser;
import com.wandering.trader.domain.Task;
import com.wandering.trader.services.ProjectService;
import com.wandering.trader.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    ProjectService projectService;

    @GetMapping
    public String getTasks(@RequestParam Long id, Model model, Authentication authentication){
        model.addAttribute("task", new Task());
        model.addAttribute("types", Task.TYPE.values());
        model.addAttribute("projectId", id);
        model.addAttribute("statuses", Task.STATUS.values());
        model.addAttribute("toDos", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.TODO));
        model.addAttribute("thisWeeks", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.THISWEEK));
        model.addAttribute("todays", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.TODAY));
        model.addAttribute("inProgresses", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.INPROGRESS));
        model.addAttribute("dones", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.DONE));
        return "task_manager";
    }

    @PostMapping("new")
    public String createNewTask(@RequestParam Long projectId, Task task, Authentication authentication){
        if (!authentication.isAuthenticated())
            return "redirect:/login";
        task.setCreatedAt(LocalDateTime.now());
        task.setStatus(Task.STATUS.TODO);
        task.setUpdatedAt(task.getCreatedAt());
        task.setOwner((CustomUser)authentication.getPrincipal());
        task.setProject(projectService.getProjectByID(projectId).get());
        taskService.saveOrUpdateTask(task);
        return "redirect:/admin/tasks?id="+projectId;
    }
}
