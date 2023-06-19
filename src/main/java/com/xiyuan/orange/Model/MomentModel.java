package com.xiyuan.orange.Model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MomentModel {
    private int moment_id;
    private String content;
    private String poster_openid;
    private LocalDateTime create_time;
    private String nick_name;
    private String avatar;
    private String school_name;
    private List<ImageModel> image_list;
}
