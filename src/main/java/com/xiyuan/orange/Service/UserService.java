package com.xiyuan.orange.Service;

import cn.hutool.Hutool;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONParser;
import com.alibaba.fastjson.JSONObject;
import com.xiyuan.orange.Mapper.UserMapper;
import com.xiyuan.orange.Model.Code2SessionModel;
import com.xiyuan.orange.Model.UserModel;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;
    @Value("${appid}")
    String APPID;
    @Value("${app-secret}")
    String APP_SECRET;
    public Map<String,Object> login(String code) {
        Map<String,Object> data = new HashMap<>();
        data.put("appid",APPID);
        data.put("secret",APP_SECRET);
        data.put("js_code",code);
        data.put("grant_type","authorization_code");
        String body = HttpUtil.createGet("https://api.weixin.qq.com/sns/jscode2session").form(data).execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        Code2SessionModel code2SessionModel = jsonObject.toJavaObject(Code2SessionModel.class);
        String openid = code2SessionModel.getOpenid();
        UserModel user = userMapper.getUserByOpenid(openid);
        Map<String,String> payload = new HashMap<>();
        payload.put("openid",openid);
        if(user != null) {
            String token = JWTUtils.getToken(payload);
            Map<String,Object> result = new HashMap<>();
            result.put("token",token);
            result.put("user_info",user);
            return result;
        }
        if (userMapper.createUser(openid) > 0) {
            String token = JWTUtils.getToken(payload);
            Map<String,Object> result = new HashMap<>();
            result.put("token",token);
            return result;
        }
        return null;
    }
    public UserModel getUserByOpenid(String openid) {
        UserModel user = userMapper.getUserByOpenid(openid);
        return user;
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
