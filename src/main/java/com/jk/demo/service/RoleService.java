package com.jk.demo.service;

import com.jk.demo.entities.Role;
import com.jk.demo.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService implements RoleMapper {
    @Autowired
    RoleMapper roleMapper;
    public Collection<Role> getAll(){
        return roleMapper.getAll();
    }
    public Role findById(String id){
        return roleMapper.findById(id);
    }

    public void saveRole(Role role){
        roleMapper.saveRole(role);
    }

    public void updateRole(Role role){
        roleMapper.updateRole(role);
    }

    public void deleteRole(String id){
        roleMapper.deleteRole(id);
    }
}
