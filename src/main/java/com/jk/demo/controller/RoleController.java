package com.jk.demo.controller;

import com.jk.demo.entities.Role;
import com.jk.demo.service.RoleService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/rolemanage")
    public String list(Model model){
        Collection<Role> roles = roleService.getAll();
        model.addAttribute("roles",roles);
        return "role";
    }

    @GetMapping("/emp_role")
    public String toAddPage(){
        return "add_role";
    }

    @PostMapping("/emp_role")
    public String addRole(Role role){

        System.out.println("保存的角色信息"+role.toString());
        if(roleService.findById(role.getId()) == null)
            roleService.saveRole(role);
        else roleService.updateRole(role);
        return "redirect:/rolemanage";

    }
    @DeleteMapping("/emp_role/{id}")
    public String delete(@PathVariable("id") String id){
        roleService.deleteRole(id);
        return "redirect:/rolemanage";
    }

    @GetMapping("/emp_role/{id}")
    public String toEditPage(@PathVariable("id") String id,Model model){
        Role byId = roleService.findById(id);
        model.addAttribute("role",byId);
        return "add_role";
    }
}
