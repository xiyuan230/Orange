package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.UserDetailModel;
import com.xiyuan.orange.Service.UserDetailService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class UserDetailController {
    @Resource
    UserDetailService userDetailService;
    @GetMapping("/user/detail")
    public R getUserDetailByOpenid(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        UserDetailModel userDetail = userDetailService.getUserDetailByOpenid(openid);
        return R.success(userDetail).setMsg("获取详细信息成功");
    }

    @PutMapping("/user/detail")
    public R updateUserDetail(HttpServletRequest req, @RequestBody UserDetailModel userDetail) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        userDetail.setOpenid(openid);
        if (!userDetailService.updateUserDetail(userDetail)) {
            return R.error("更新用户详情失败");
        }
        return R.success().setMsg("更新用户详情成功");
    }
}
