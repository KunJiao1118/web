package com.jk.demo.service;

import com.jk.demo.entities.User;
import com.jk.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService implements UserMapper {

    @Autowired
    UserMapper userMapper;
    @Override
    public User findById(String id) {
        return userMapper.findById(id);
    }
    public Collection<User> getAll(){
        return userMapper.getAll();
    }


    public void saveUser(User user){
        userMapper.saveUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public void delete(String id){
        userMapper.delete(id);
    }
}
