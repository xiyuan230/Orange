package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where openid = #{openid}")
    UserModel getUserByOpenid(String openid);
    @Update("update users set avatar = #{avatar} where openid = #{openid}")
    int updateUserAvatar(UserModel user);
    @Update("update users set nick_name = #{nick_name},gender = #{gender},phone = #{phone},description = #{description} where openid = #{openid}")
    int updateUser(UserModel user);
    @Insert("insert into users(openid) values(#{openid})")
    int createUser(String openid);
//    用户管理
    @Select("select count(*) from users")
    int getUserListCount();
    @Select("select * from users limit #{start},#{end}")
    List<UserModel> getUserList(int start,int end);
    @Update("update users set status = #{status} where openid = #{openid}")
    int updateUserStatus();
}
