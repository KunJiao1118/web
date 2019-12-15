package com.jk.demo.controller;

import com.jk.demo.entities.Me;
import com.jk.demo.service.MeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    MeService meService;
    /*
    @PostMapping(value = "/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){
        if(!StringUtils.isEmpty(username) && "1".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/users";
        }else{
         //   model.addAttribute("msg","用户名或者密码错误");
            map.put("msg","用户名或者密码错误");
            return "redirect:/index";
        }
    }
    */
    @PostMapping("/user/login")
    public String login(Me me){
        System.out.println("保存的角色信息"+me.toString());
       /* if(meService.getByName(me.getUsername()) == null){
            meService.save(me);
            return "redirect:/users";
        }

        else {
            //meService.update(me);
            if(meService.getByName(me.getUsername()).getPassword().equals(me.getPassword())){
                return "redirect:/users";
            }
            else return "redirect:/indexnot";
        }*/
       return "main";

    }
}
