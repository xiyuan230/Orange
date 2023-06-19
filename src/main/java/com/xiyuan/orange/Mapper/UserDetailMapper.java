package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.UserDetailModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserDetailMapper {
    @Select("select d.*,s.school_name from user_detail d left join schools s on d.school_id = s.id where openid = #{openid}")
    UserDetailModel getUserDetailByOpenid(String openid);
    @Insert("insert into user_detail values(#{openid},#{province},#{city},#{district},#{education},#{school_id},#{college},#{admission_date},#{address})")
    int createUserDetail(UserDetailModel userDetail);
    @Update("update user_detail set province = #{province}, city = #{city}, district = #{district}, education = #{education},school_id = #{school_id},college = #{college},admission_date = #{admission_date},address = #{address} where openid = #{openid}")
    int updateUserDetail(UserDetailModel userDetail);
}
