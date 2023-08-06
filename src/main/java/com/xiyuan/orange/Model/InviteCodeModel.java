package com.xiyuan.orange.Model;

import lombok.Data;

@Data
public class InviteCodeModel {
    private int id;
    private String user_openid;
    private String code;
}
