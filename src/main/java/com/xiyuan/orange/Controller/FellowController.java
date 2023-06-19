package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.FellowModel;
import com.xiyuan.orange.Model.UserApplyModel;
import com.xiyuan.orange.Model.UserModel;
import com.xiyuan.orange.Service.FellowService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FellowController {
    @Resource
    FellowService fellowService;
    @GetMapping("/fellow")
    public R getFellowList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        List<FellowModel> fellowList = fellowService.getFellowList(openid);
        return R.success(fellowList).setMsg("获取老乡列表成功");
    }
    @PostMapping("/apply")
    public R createUserApply(@RequestBody UserApplyModel apply,HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        apply.setUser_openid(openid);
        if (fellowService.createUserApply(apply)) {
            return R.success().setMsg("发送申请成功");
        }
        return R.error("已发送");
    }
    @GetMapping("/apply")
    public R getApplyList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        List<UserApplyModel> applyList = fellowService.getApplyList(openid);
        return R.success(applyList).setMsg("获取申请列表成功");
    }

    @GetMapping("/friend")
    public R getFriendList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        List<Map<String, String>> friendList = fellowService.getFriendList(openid);
        return R.success(friendList).setMsg("获取好友列表成功");
    }

    @PutMapping("/apply/accept/{apply_id}")
    public R acceptUserApply(@PathVariable int apply_id,HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        if (fellowService.acceptUserApply(apply_id,openid)) {
            return R.success().setMsg("成功添加好友");
        }
        return R.error("添加好友失败");
    }
    @PutMapping("/apply/refuse/{apply_id}")
    public R refuseUserApply(@PathVariable int apply_id) {
        if (fellowService.refuseUserApply(apply_id)) {
            return R.success().setMsg("已拒绝申请");
        }
        return R.error("拒绝申请出错");
    }
    @DeleteMapping("/friend/{friend_openid}")
    public R deleteFriend(@PathVariable String friend_openid,HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        if(fellowService.deleteFriend(openid,friend_openid)) {
            return R.success().setMsg("删除好友成功");
        }
        return R.error("删除好友失败");
    }
    @GetMapping("/fellow/count")
    public R getFellowCount(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        Map<String, String> fellowCount = fellowService.getFellowCount(openid);
        return R.success(fellowCount).setMsg("获取老乡数量成功");
    }
}
