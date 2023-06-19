package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.SchoolModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SchoolMapper {
    @Select("select * from schools")
    List<SchoolModel> getSchoolList();
    @Insert("insert into schools(school_name) values(#{school_name})")
    int createSchool(String school_name);
    @Update("update schools set school_name = #{school_name} where id = #{id}")
    int updateSchool(SchoolModel school);
    @Delete("delete from schools where id = #{id}")
    int deleteSchool(int id);
}
