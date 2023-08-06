package com.xiyuan.orange.Service;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Mapper.InviteCodeMapper;
import com.xiyuan.orange.Mapper.UserMapper;
import com.xiyuan.orange.Model.InviteCodeModel;
import com.xiyuan.orange.Model.UserModel;
import com.xiyuan.orange.Utils.JWTUtils;
import com.xiyuan.orange.Utils.WeChatUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    @Resource
    InviteCodeMapper inviteCodeMapper;
    public R login(String code) {
        String openid = WeChatUtils.getUserOpenid(code);
        UserModel user = userMapper.getUserByOpenid(openid);
        Map<String,String> payload = new HashMap<>();
        if(user == null) {
            return R.error("新用户请填写邀请码注册").setCode(403);
        }
        payload.put("openid",openid);
        String token = JWTUtils.getToken(payload);
        Map<String,Object> result = new HashMap<>();
        result.put("token",token);
        result.put("user_info",user);
        return R.success(result).setMsg("登陆成功");
    }
    public R register(String code, String invite_code) {
        InviteCodeModel invite = inviteCodeMapper.getInviteCodeByCode(invite_code);
        if (invite == null) {
            return R.error("邀请码不存在").setCode(403);
        }
        String openid = WeChatUtils.getUserOpenid(code);
        if (userMapper.createUser(openid) < 1) {
            return R.error("注册失败");
        }
        UserModel user = userMapper.getUserByOpenid(openid);
        Map<String,String> payload = new HashMap<>();
        payload.put("openid",openid);
        String token = JWTUtils.getToken(payload);
        Map<String,Object> result = new HashMap<>();
        result.put("token",token);
        result.put("user_info",user);
        return R.success(result).setMsg("注册成功");
    }
    public UserModel getUserByOpenid(String openid) {
        return userMapper.getUserByOpenid(openid);
    }

    public boolean updateUserAvatar(UserModel userModel) {
        int row = userMapper.updateUserAvatar(userModel);
        return row > 0;
    }

    public boolean updateUser(UserModel userModel) {
        int row = userMapper.updateUser(userModel);
        return row > 0;
    }

    public Map<String,Integer> getUserInfoCount(String openid) {
        Map<String,Integer> map = new HashMap<>();
        int friend = userMapper.getUserFriendCount(openid);
        int moment = userMapper.getUserMomentCount(openid);
        int order = userMapper.getUserProductOrderCount(openid);
        map.put("friend_count",friend);
        map.put("moment_count",moment);
        map.put("order_count",order);
        return map;
    }


}
