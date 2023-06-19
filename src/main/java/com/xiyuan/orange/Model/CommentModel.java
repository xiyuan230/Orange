package com.xiyuan.orange.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentModel {
    private int comment_id;
    private  String author_openid;
    private String author_avatar;
    private String author_name;
    private String content;
    private int moment_id;
    private int type;
    private int reply_id;
    private String reply_content;
    private String reply_name;
    private LocalDateTime comment_time;
}
