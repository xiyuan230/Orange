package com.xiyuan.orange.Model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserModel {
    private String openid;
    private String nick_name;
    private int gender;
    private String phone;
    private String avatar;
    private String description;
    private int role;
    private int status;
    private LocalDateTime regist_time;
}
