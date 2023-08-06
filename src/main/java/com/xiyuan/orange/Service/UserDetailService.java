package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.UserDetailMapper;
import com.xiyuan.orange.Model.UserDetailModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserDetailService {
    @Resource
    UserDetailMapper userDetailMapper;

    public UserDetailModel getUserDetailByOpenid(String openid) {
        UserDetailModel userDetail = userDetailMapper.getUserDetailByOpenid(openid);
        return userDetail;
    }

    public boolean createUserDetail(UserDetailModel userDetail) {
        return userDetailMapper.createUserDetail(userDetail)>0;
    }
    public boolean updateUserDetail(UserDetailModel userDetail) {
        UserDetailModel detail = userDetailMapper.getUserDetailByOpenid(userDetail.getOpenid());
        if (detail == null) {
            return userDetailMapper.createUserDetail(userDetail)>0;
        }
        return userDetailMapper.updateUserDetail(userDetail) > 0;
    }
}
