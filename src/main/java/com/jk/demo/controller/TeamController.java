package com.jk.demo.controller;

import com.jk.demo.entities.Team;
import com.jk.demo.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;

@Controller
public class TeamController {
    @Autowired
    TeamService teamService;
    /*
    @GetMapping("/jk")
    public String jk(){
        System.out.println(teamService.findById("1"));
        return "index";
    }
    */
    @GetMapping("/teammanage")
    public String list(Model model){
        Collection<Team> teams = teamService.getAll();
        model.addAttribute("teams",teams);
        return "team";
    }

    @GetMapping("/emp_team")
    public String toAddPage(){
        return "add_team";
    }
    @PostMapping("/emp_team")
    public String addTeam(Team team){
        System.out.println("保存的团队信息"+team.toString());
        if(teamService.findById(team.getId()) == null)
        teamService.saveTeam(team);
        else teamService.updateTeam(team);
        return "redirect:/teammanage";
    }

    @GetMapping("/emp_team/{id}")
    public String toEditPage(@PathVariable("id") String id,Model model){
        Team byId = teamService.findById(id);
        model.addAttribute("team",byId);
        return "add_team";

    }
    @DeleteMapping("/emp_team/{id}")
    public String delete(@PathVariable("id") String id){
        teamService.delete(id);
        return "redirect:/teammanage";
    }

}
