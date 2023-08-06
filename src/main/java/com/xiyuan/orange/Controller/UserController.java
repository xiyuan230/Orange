package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.LoginFromModel;
import com.xiyuan.orange.Model.UserModel;
import com.xiyuan.orange.Service.UserService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Resource
    UserService userService;

    @PostMapping("/login")
    public R login(@RequestBody LoginFromModel form) {
        return userService.login(form.getCode());
    }
    @PostMapping("/register")
    public R register(@RequestBody Map<String,String> data) {
        return userService.register(data.get("code"),data.get("invite_code"));
    }
    @GetMapping("/user")
    public R getUserByOpenid(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        UserModel user = userService.getUserByOpenid(openid);
        if(user == null) {
            return R.error("用户不存在").setCode(401);
        }
        return R.success(user).setMsg("获取个人信息成功");
    }

    @PutMapping("/user/avatar")
    public R updateUserAvatar(HttpServletRequest req, @RequestBody UserModel user) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        user.setOpenid(openid);
        if (!userService.updateUserAvatar(user)) {
            return R.error("更新头像失败");
        }
        return R.success().setMsg("更新头像成功");

    }

    @PutMapping("/user")
    public R updateUserr(HttpServletRequest req, @RequestBody UserModel user) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        user.setOpenid(openid);
        if (!userService.updateUser(user)) {
            return R.error("更新信息失败");
        }
        return R.success().setMsg("更新信息成功");
    }



    @GetMapping("/test")
    public String test() {
        Map<String,String> payload = new HashMap<>();
        payload.put("openid","xiyuan");
        String token = JWTUtils.getToken(payload);
        return token;
    }
    @GetMapping("/user/count")
    public R getUserInfCount(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        Map<String, Integer> userInfoCount = userService.getUserInfoCount(openid);
        return R.success(userInfoCount).setMsg("获取用户信息成功");
    }
}
