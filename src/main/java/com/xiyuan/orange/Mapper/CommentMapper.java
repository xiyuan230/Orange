package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.CommentModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentMapper {
    @Select("SELECT c.*,c1.content AS reply_content,u.nick_name AS author_name,u.avatar as author_avatar,u1.nick_name AS reply_name FROM ((comments c LEFT JOIN comments c1 ON c.reply_id = c1.comment_id) LEFT JOIN users u ON c.author_openid = u.openid) LEFT JOIN users u1 ON c1.author_openid = u1.openid WHERE c.moment_id = #{moment_id} ORDER BY c.comment_time ASC")
    List<CommentModel> getCommentList(int moment_id);
    @Insert("INSERT INTO comments(author_openid,content,moment_id,type,reply_id,comment_time) VALUES(#{author_openid},#{content},#{moment_id},#{type},#{reply_id},#{comment_time})")
    int createComment(CommentModel comment);
}
