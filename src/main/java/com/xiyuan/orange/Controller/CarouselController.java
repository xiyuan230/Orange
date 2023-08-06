package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Service.CarouselService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
public class CarouselController {
    @Resource
    CarouselService carouselService;
    @GetMapping("/carousel")
    public R getCarouseList() {
        return carouselService.getCarouselList();
    }
}
