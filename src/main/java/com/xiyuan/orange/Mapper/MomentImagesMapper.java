package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.ImageModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface MomentImagesMapper {
    @Select("select * from moment_images where moment_id = #{moment_id}")
    List<ImageModel> getMomentImagesByMomentID(int moment_id);
    @Insert("insert into moment_images(url,moment_id) values(#{url},#{moment_id})")
    int createMomentImage(ImageModel image);
}
