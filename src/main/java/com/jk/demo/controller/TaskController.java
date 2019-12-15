package com.jk.demo.controller;

import com.jk.demo.entities.Task;
import com.jk.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;

@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @GetMapping("/task")
    public String list(Model model){
        Collection<Task> tasks = taskService.getNotDone();
        model.addAttribute("tasks",tasks);
        return "task";
    }

    @GetMapping("/taskdone")
    public String listdone(Model model){
        Collection<Task> tasks = taskService.getDone();
        model.addAttribute("tasks",tasks);
        return "taskdone";
    }
}
