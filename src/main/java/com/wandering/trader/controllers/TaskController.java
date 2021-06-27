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
import java.util.Optional;

@Controller
@RequestMapping("/admin/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    ProjectService projectService;

    @GetMapping
    public String getTasks(@RequestParam Long id, Model model, Authentication authentication){
        model.addAttribute("projectList", projectService.getAllProjects());
        model.addAttribute("task", new Task());
        model.addAttribute("types", Task.TYPE.values());
        model.addAttribute("projectId", id);
        model.addAttribute("statuses", Task.STATUS.values());
        model.addAttribute("toDos", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.TODO));
        model.addAttribute("thisWeeks", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.THISWEEK));
        model.addAttribute("todays", taskService.getTaskListByProjectIdAndOwnerAndStatus(id,(CustomUser)authentication.getPrincipal(),Task.STATUS.TODAY));
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

    @PostMapping("updateStatus")
    public String updateStatusOfTask(@RequestParam Long projectId, @RequestParam Long taskId){
        Optional<Task> taskOptional = taskService.findTaskById(taskId);
        if (!taskOptional.isPresent())
            return "redirect:/admin/tasks?id="+projectId;

        if(taskService.findTaskById(taskId).get().getStatus().equals(Task.STATUS.TODO)){
            taskOptional.get().setStatus(Task.STATUS.THISWEEK);
            taskService.saveOrUpdateTask(taskOptional.get());
        }else {
            if(taskService.findTaskById(taskId).get().getStatus().equals(Task.STATUS.THISWEEK)){
                taskOptional.get().setStatus(Task.STATUS.TODAY);
                taskService.saveOrUpdateTask(taskOptional.get());
            } else {
                if(taskService.findTaskById(taskId).get().getStatus().equals(Task.STATUS.TODAY)){
                    taskOptional.get().setStatus(Task.STATUS.DONE);
                    taskService.saveOrUpdateTask(taskOptional.get());
                }
                else {
                    if(taskService.findTaskById(taskId).get().getStatus().equals(Task.STATUS.DONE)){
                        taskOptional.get().setStatus(Task.STATUS.TODO);
                        taskService.saveOrUpdateTask(taskOptional.get());
                    }
                }
            }
        }
        return "redirect:/admin/tasks?id="+projectId;
    }

    @GetMapping("edit")
    public String editTaskGet(@RequestParam Long taskId, Model model){
        model.addAttribute("types", Task.TYPE.values());
        model.addAttribute("task", taskService.findTaskById(taskId));
        model.addAttribute("projectId", taskService.findTaskById(taskId).get().getProject().getId());
        return"edit_task";
    }

    @PostMapping("edit")
    public String saveTask(@RequestParam Long projectId, Task task){
        taskService.saveOrUpdateTask(task);
        return"redirect:/admin/tasks?id="+projectId;
    }
}
