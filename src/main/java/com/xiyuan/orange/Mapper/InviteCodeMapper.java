package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.InviteCodeModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface InviteCodeMapper {
    @Select("SELECT * FROM invite_code WHERE code = #{code}")
    InviteCodeModel getInviteCodeByCode(String code);
    @Select("INSERT INTO invite_code(user_openid,code) VALUES(#{user_openid},#{code})")
    int createInviteCode(InviteCodeModel inviteCode);
}
