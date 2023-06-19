package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.FellowMapper;
import com.xiyuan.orange.Model.FellowModel;
import com.xiyuan.orange.Model.UserApplyModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FellowService {
    @Resource
    FellowMapper fellowMapper;

    public List<FellowModel> getFellowList(String openid) {
        return fellowMapper.getFellowList(openid);
    }
    public boolean createUserApply(UserApplyModel apply) {
        if (fellowMapper.getApplyByOpenid(apply) == null) {
            return fellowMapper.createUserApply(apply)>0;
        }
        return false;
    }

    public List<UserApplyModel> getApplyList(String openid){
        return fellowMapper.getApplyList(openid);
    }

    public List<Map<String, String>> getFriendList(String openid) {
        return fellowMapper.getFriendList(openid);
    }

    public boolean acceptUserApply(int apply_id,String openid){
        if(fellowMapper.acceptUserApply(apply_id) > 0) {
            UserApplyModel apply = fellowMapper.getApplyByID(apply_id);
            if (fellowMapper.createFriendRelation(openid,apply.getUser_openid()) > 0 && fellowMapper.createFriendRelation(apply.getUser_openid(),openid) >0) {
                return true;
            }
            return false;
        }
        return false;
    }
    public boolean refuseUserApply(int apply_id){
        if(fellowMapper.deleteApply(apply_id) > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteFriend(String user_openid,String friend_openid) {
        if (fellowMapper.deleteFriend(user_openid,friend_openid) > 0) {
            fellowMapper.deleteFriend(friend_openid,user_openid);
            fellowMapper.deleteApplyByOpenid(user_openid,friend_openid);
            fellowMapper.deleteApplyByOpenid(friend_openid,user_openid);
            return true;
        }
        return false;
    }

    public Map<String,String> getFellowCount(String openid) {
        return fellowMapper.getFellowCount(openid);
    }
}
