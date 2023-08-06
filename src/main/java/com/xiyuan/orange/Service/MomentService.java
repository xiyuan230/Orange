package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.MomentImagesMapper;
import com.xiyuan.orange.Mapper.MomentMapper;
import com.xiyuan.orange.Model.ImageModel;
import com.xiyuan.orange.Model.MomentModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MomentService {
    @Resource
    MomentMapper momentMapper;
    @Resource
    MomentImagesMapper momentImagesMapper;
    public List<MomentModel> getMomentList(int page,int size){
        List<MomentModel> momentList = momentMapper.getMomentList((page - 1) * size, size);
        for (MomentModel moment: momentList) {
            List<ImageModel> momentImagesByMomentID = momentImagesMapper.getMomentImagesByMomentID(moment.getMoment_id());
            moment.setImage_list(momentImagesByMomentID);
        }
        return momentList;
    }
    public List<MomentModel> getMomentListBySearch(int page,int size,String query){
        List<MomentModel> momentList = momentMapper.getMomentListBySearch((page - 1) * size, size,query);
        for (MomentModel moment: momentList) {
            List<ImageModel> momentImagesByMomentID = momentImagesMapper.getMomentImagesByMomentID(moment.getMoment_id());
            moment.setImage_list(momentImagesByMomentID);
        }
        return momentList;
    }
    public List<MomentModel> getMomentListByOpenid(String openid) {
        List<MomentModel> list = momentMapper.getMomentListByOpenid(openid);
        return list;
    }
    public boolean deleteMomentByID(int moment_id,String openid) {
        MomentModel momentByID = momentMapper.getMomentByID(moment_id);
        if(!momentByID.getPoster_openid().equals(openid)) {
            return false;
        }
        if(momentMapper.deleteMomentByID(moment_id) < 1) {
            return false;
        }
        return true;
    }
    public MomentModel getMomentByID(int id) {
        MomentModel moment = momentMapper.getMomentByID(id);
        List<ImageModel> images = momentImagesMapper.getMomentImagesByMomentID(id);
        moment.setImage_list(images);
        return moment;
    }

    public boolean createMoment(MomentModel moment) {
        moment.setCreate_time(LocalDateTime.now());
        if (momentMapper.createMoment(moment) == 0) {
            return false;
        }
        for (ImageModel image : moment.getImage_list()) {
            image.setMoment_id(moment.getMoment_id());
            momentImagesMapper.createMomentImage(image);
        }
        return true;
    }
}
