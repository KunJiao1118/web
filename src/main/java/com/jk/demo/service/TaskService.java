package com.jk.demo.service;

import com.jk.demo.entities.Task;
import com.jk.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TaskService implements TaskMapper {

    @Autowired
    TaskMapper taskMapper;
    public Task findByName(String name){
        return taskMapper.findByName(name);
    }

    public Collection<Task> getAll(){
        return taskMapper.getAll();
    }

    public Collection<Task> getDone(){
        return taskMapper.getDone();
    }
    public  Collection<Task> getNotDone(){
        return taskMapper.getNotDone();
    }
}
