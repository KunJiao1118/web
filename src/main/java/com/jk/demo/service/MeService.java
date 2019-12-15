package com.jk.demo.service;

import com.jk.demo.entities.Me;
import com.jk.demo.mapper.MeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeService implements MeMapper {
    @Autowired
    MeMapper meMapper;
    public void save(Me me){
        meMapper.save(me);
    }
    public void update(Me me){
        meMapper.update(me);
    }
    public Me getByName(String username){
        return meMapper.getByName(username);
    }

}
