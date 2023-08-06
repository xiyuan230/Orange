package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.MomentModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MomentMapper {
    @Select("SELECT m.*,u.nick_name,u.avatar,s.school_name FROM ((moments m LEFT JOIN users u on m.poster_openid = u.openid) LEFT JOIN user_detail u_d ON m.poster_openid = u_d.openid) LEFT JOIN schools s ON s.id = u_d.school_id ORDER BY m.create_time DESC LIMIT #{offset},#{size}")
    List<MomentModel> getMomentList(@Param("offset") int offset, @Param("size") int size);
    @Select("SELECT m.*,u.nick_name,u.avatar,s.school_name FROM ((moments m LEFT JOIN users u on m.poster_openid = u.openid) LEFT JOIN user_detail u_d ON m.poster_openid = u_d.openid) LEFT JOIN schools s ON s.id = u_d.school_id WHERE m.content LIKE CONCAT('%',#{query},'%') ORDER BY m.create_time DESC LIMIT #{offset},#{size}")
    List<MomentModel> getMomentListBySearch(@Param("offset") int offset, @Param("size") int size,@Param("query") String query);
    @Insert("INSERT INTO moments (content, poster_openid, create_time) VALUES (#{content}, #{poster_openid}, #{create_time})")
    @Options(useGeneratedKeys = true,keyProperty = "moment_id",keyColumn = "moment_id")
    int createMoment(MomentModel moment);
    @Select("SELECT m.*,u.nick_name,u.avatar,s.school_name FROM ((moments m LEFT JOIN users u on m.poster_openid = u.openid) LEFT JOIN user_detail u_d ON m.poster_openid = u_d.openid) LEFT JOIN schools s ON s.id = u_d.school_id WHERE m.moment_id = #{id}")
    MomentModel getMomentByID(int id);
    @Select("SELECT m.*,u.nick_name,u.avatar FROM moments m JOIN users u ON u.openid = m.poster_openid WHERE m.poster_openid = #{openid}")
    List<MomentModel> getMomentListByOpenid(String openid);
    @Delete("DELETE FROM moments WHERE moment_id = #{moment_id}")
    int deleteMomentByID(int moment_id);
}
