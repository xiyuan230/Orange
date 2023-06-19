package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.CommentMapper;
import com.xiyuan.orange.Model.CommentModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Resource
    CommentMapper commentMapper;
    public List<CommentModel> getCommentList(int moment_id) {
        List<CommentModel> commentList = commentMapper.getCommentList(moment_id);
        return commentList;
    }

    public boolean createComment(CommentModel comment) {
        comment.setComment_time(LocalDateTime.now());
        return commentMapper.createComment(comment) > 0;
    }
}
