package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.FellowModel;
import com.xiyuan.orange.Model.UserApplyModel;
import com.xiyuan.orange.Model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FellowMapper {
    @Select("SELECT u.openid,u.nick_name,u.avatar,u_d.admission_date,u_d.education,u_d.college FROM user_detail u_d JOIN users u on u.openid = u_d.openid WHERE u_d.province = (SELECT province FROM user_detail WHERE openid = #{openid}) AND u_d.openid != #{openid}")
    List<FellowModel> getFellowList(String openid);
    @Select("SELECT u_a.apply_id,u.nick_name,u.avatar,u.gender,u_d.education,u_d.province,u_a.status From user_apply u_a JOIN users u ON u.openid = u_a.user_openid LEFT JOIN user_detail u_d on u_d.openid = u.openid WHERE u_a.target_openid = #{openid}")
    List<UserApplyModel> getApplyList(String openid);
    @Select("SELECT * FROM user_apply WHERE target_openid = #{target_openid} AND user_openid = #{user_openid}")
    UserApplyModel getApplyByOpenid(UserApplyModel apply);
    @Select("SELECT * FROM user_apply WHERE apply_id = #{apply_id}")
    UserApplyModel getApplyByID(int apply_id);
    @Select("SELECT COUNT(IF(province = (SELECT province FROM user_detail WHERE openid = #{openid}),1,NULL)) as province, COUNT(IF(city = (SELECT city FROM user_detail WHERE openid = #{openid}),1,NULL)) as city, COUNT(IF(district = (SELECT district FROM user_detail WHERE openid = #{openid}),1,NULL)) as district FROM user_detail")
    Map<String,String> getFellowCount(String openid);
    @Insert("INSERT INTO user_apply(user_openid,target_openid) VALUES(#{user_openid},#{target_openid})")
    int createUserApply(UserApplyModel userApply);
    @Update("UPDATE user_apply SET status = 1 WHERE apply_id=#{apply_id}")
    int acceptUserApply(int apply_id);
    @Select("SELECT u.openid,u.avatar,u.nick_name,u.gender,u.phone,u_d.province,s.school_name,u_d.college FROM user_relation u_r LEFT JOIN users u ON u.openid = u_r.friend_openid LEFT JOIN user_detail u_d ON u.openid = u_d.openid LEFT JOIN schools s ON s.id = u_d.school_id WHERE user_openid = #{user_openid}")
    List<Map<String,String>> getFriendList(String user_openid);
    @Insert("INSERT INTO user_relation(user_openid,friend_openid) VALUES(#{user_openid},#{friend_openid})")
    int createFriendRelation(@Param("user_openid") String user_openid,@Param("friend_openid") String friend_openid);
    @Delete("DELETE FROM user_relation WHERE friend_openid = #{friend_openid} AND user_openid = #{user_openid}")
    int deleteFriend(@Param("user_openid") String user_openid,@Param("friend_openid") String friend_openid);
    @Delete("DELETE FROM user_apply WHERE apply_id = #{apply_id}")
    int deleteApply(int apply_id);
    @Delete("DELETE FROM user_apply WHERE user_openid = #{user_openid} AND target_openid = #{target_openid}")
    int deleteApplyByOpenid(@Param("user_openid") String user_openid,@Param("target_openid") String target_openid );
    @Select("SELECT count(*) FROM user_relation WHERE (user_openid = #{user_openid} AND friend_openid = #{friend_openid}) OR (user_openid = #{friend_openid} AND friend_openid = #{user_openid})")
    int queryRelationCount(@Param("user_openid") String user_openid,@Param("friend_openid") String friend_openid);
}
