package com.jk.demo.service;

import com.jk.demo.entities.Team;
import com.jk.demo.mapper.TeamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TeamService implements TeamMapper {
    @Autowired
    TeamMapper teamMapper;
    public Team findById(String id){
        return teamMapper.findById(id);
    }

    public Collection<Team> getAll(){
        return teamMapper.getAll();
    }

    public void saveTeam(Team team){
        teamMapper.saveTeam(team);
    }

    public void updateTeam(Team team){
        teamMapper.updateTeam(team);
    }
    public  void delete(String id){
        teamMapper.delete(id);
    }
}
