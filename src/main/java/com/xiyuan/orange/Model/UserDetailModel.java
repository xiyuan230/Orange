package com.xiyuan.orange.Model;

import lombok.Data;

@Data
public class UserDetailModel {
    private String openid;
    private String province;
    private String city;
    private String district;
    private int education;
    private String address;
    private int school_id;
    private String school_name;
    private String college;
    private String admission_date;

}
