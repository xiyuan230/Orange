package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.MomentModel;
import com.xiyuan.orange.Service.MomentService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MomentController {
    @Resource
    MomentService momentService;
    @GetMapping("/moment")
    public R getMomentList(int page,int size){
        List<MomentModel> momentList = momentService.getMomentList(page, size);
        return R.success(momentList).setMsg("获取列表成功");
    }
    @GetMapping("/moment/search")
    public R getMomentListBySearch(int page,int size,String query){
        List<MomentModel> momentList = momentService.getMomentListBySearch(page, size, query);
        return R.success(momentList).setMsg("获取列表成功");
    }
    @GetMapping("/moment/{moment_id}")
    public R getMomentByID(@PathVariable int moment_id) {
        MomentModel moment = momentService.getMomentByID(moment_id);
        return R.success(moment).setMsg("获取动态详情成功");
    }
    @PostMapping("/moment")
    public R createMoment(@RequestBody MomentModel moment, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        moment.setPoster_openid(openid);
        if (momentService.createMoment(moment)) {
            return R.success().setMsg("发布成功");
        }
        return R.error("发布失败");
    }
    @GetMapping("/moment/publish")
    public R getMomentListByOpenid(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        List<MomentModel> list = momentService.getMomentListByOpenid(openid);
        return R.success(list).setMsg("获取动态列表成功");
    }
    @DeleteMapping("/moment/publish/{moment_id}")
    public R deleteMomentByID(@PathVariable int moment_id,HttpServletRequest req){
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        if (!momentService.deleteMomentByID(moment_id,openid)) {
            return R.error("删除失败");
        }
        return R.success().setMsg("删除成功");
    }
}
