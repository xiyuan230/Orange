package com.xiyuan.orange.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface CarouselMapper {
    @Select("SELECT url FROM carousel")
    List<String> getCarouselList();
}
