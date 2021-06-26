package com.wandering.trader.controllers;

import com.wandering.trader.domain.Task;
import com.wandering.trader.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin/tasks")
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping
    public String getTasks(@RequestParam Long id, Model model){
        model.addAttribute("ideas", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.IDEA));
        model.addAttribute("bugs", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.BUG));
        model.addAttribute("issues", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.ISSUE));
        model.addAttribute("features", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.FEATURE));
        model.addAttribute("enhancements", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.ENHANCEMENT));
        model.addAttribute("thisweek", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.THISWEEK));
        model.addAttribute("today", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.TODAY));
        model.addAttribute("test", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.TEST));
        model.addAttribute("done", taskService.getTaskListByProjectIdAndStatus(id,Task.STATUS.DONE));
        return "task_manager";
    }
}
