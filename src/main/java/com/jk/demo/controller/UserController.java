package com.jk.demo.controller;

import com.jk.demo.entities.Employee;
import com.jk.demo.entities.User;
import com.jk.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping("/userprint")
    @ResponseBody
    public String userprint(){
        return userService.findById("1").toString();
    }

    /**
     * 查询所有员工返回列表页面
     */
    @GetMapping("/users")
    public String list(Model model){
        Collection<User> users = userService.getAll();
        model.addAttribute("users",users);
        return "main";
    }

    @GetMapping("/emp_user")
    public String toAddPage(){
        return "add_user";
    }

    @PostMapping("/emp_user")
    public String addUser(User user){

        System.out.println("保存的用户信息"+user.toString());
        if(userService.findById(user.getId()) == null)
        userService.saveUser(user);
        else userService.updateUser(user);
        //来到员工列表页面
        return "redirect:/users";
    }

    //来到修改页面，查出当前员工，再页面回显

    @GetMapping("/emp_user/{id}")
    public String toEditPage(@PathVariable("id") String id,Model model){

        User byId = userService.findById(id);
        model.addAttribute("user",byId);
        return "add_user";

    }
    @DeleteMapping("/emp_user/{id}")
    public String delete(@PathVariable("id") String id){
        userService.delete(id);
        return "redirect:/users";
    }

}
