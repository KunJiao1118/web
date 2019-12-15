package com.jk.demo.mapper;

import com.jk.demo.entities.Role;
import com.jk.demo.entities.Team;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Mapper
public interface RoleMapper {
    @Select("select * from role")
     Collection<Role> getAll();
    @Select("select * from role where id = #{id}")
     Role findById(@Param("id") String id);
    @Insert("insert into role(id,username,name,role,model) " +
            "values(#{role.id},#{role.username},#{role.name},#{role.role},#{role.model})")
    void saveRole(@Param("role") Role role);
    @Update("update role set username = #{role.username},name = #{role.name},role = #{role.role},model = #{role.model} where id = #{role.id}")
    void updateRole(@Param("role") Role role);

    @Delete("delete from role where id = #{id}")
    void deleteRole(@Param("id") String id);
}
