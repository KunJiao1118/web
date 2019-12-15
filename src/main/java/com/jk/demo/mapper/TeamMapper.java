package com.jk.demo.mapper;

import com.jk.demo.entities.Team;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

@Mapper
public interface TeamMapper {

    @Select("select * from team where id = #{id}")
    Team findById(@Param("id") String id);
    @Select("select * from team")
    Collection<Team> getAll();
    @Insert("insert into team(id,name,department,contact,email) " +
            "values(#{team.id},#{team.name},#{team.department},#{team.contact},#{team.email})")
    void saveTeam(@Param("team") Team team);
    @Update("update team set name = #{team.name},department = #{team.department},contact = #{team.contact},email = #{team.email} where id = #{team.id}")
    void updateTeam(@Param("team") Team team);
    @Delete("delete from team where id = #{id}")
    void delete(@Param("id") String id);
}
