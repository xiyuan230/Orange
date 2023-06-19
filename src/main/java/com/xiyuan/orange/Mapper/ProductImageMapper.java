package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.ImageModel;
import com.xiyuan.orange.Model.ProductImageModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductImageMapper {
    @Insert("INSERT INTO product_images(url,product_id) VALUES(#{url},#{product_id})")
    int createImage(ProductImageModel productImageModel);
    @Select("select * from product_images where product_id = #{product_id}")
    List<ProductImageModel> getProductImagesByProductID(int product_id);
}
