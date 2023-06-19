package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.CommentModel;
import com.xiyuan.orange.Service.CommentService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Resource
    CommentService commentService;
    @GetMapping("/comment/{moment_id}")
    public R getComment(@PathVariable int moment_id) {
        List<CommentModel> commentList = commentService.getCommentList(moment_id);
        return R.success(commentList).setMsg("获取评论成功");
    }

    @PostMapping("/comment")
    public R createComment(@RequestBody CommentModel comment, HttpServletRequest req){
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        comment.setAuthor_openid(openid);
        if(commentService.createComment(comment)) {
            return R.success().setMsg("评论成功");
        }
        return R.error("评论失败");
    }
}
