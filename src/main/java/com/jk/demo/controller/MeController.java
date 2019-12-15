package com.jk.demo.controller;

import com.jk.demo.entities.Me;
import com.jk.demo.entities.Role;
import com.jk.demo.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class MeController {
    @Autowired
    MeService meService;
    @GetMapping("/emp_me")
    public String list(){
        return "add_me";
    }
    @PostMapping("/emp_me")
    public String addMe(Me me){

        System.out.println("保存的角色信息"+me.toString());
        if(meService.getByName(me.getUsername()) == null)
            meService.save(me);
        else meService.update(me);
        return "redirect:/users";

    }
}
