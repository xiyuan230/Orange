package com.xiyuan.orange.Service;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Mapper.CarouselMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class CarouselService {
    @Resource
    CarouselMapper carouselMapper;
    public R getCarouselList(){
        List<String> carouselList = carouselMapper.getCarouselList();
        return R.success(carouselList).setMsg("获取轮播图成功");
    }
}
