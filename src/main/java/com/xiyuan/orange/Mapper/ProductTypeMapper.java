package com.xiyuan.orange.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductTypeMapper {
    @Select("SELECT * FROM product_type")
    List<Map<Integer,String>> getProductTypeList();
}
