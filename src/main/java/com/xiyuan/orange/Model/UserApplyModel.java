package com.xiyuan.orange.Model;

import lombok.Data;

@Data
public class UserApplyModel {
    private int apply_id;
    private String user_openid;
    private String target_openid;
    private String nick_name;
    private String avatar;
    private int gender;
    private int education;
    private String province;
    private int status;
}
