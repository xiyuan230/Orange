package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.ProductOrderModel;
import com.xiyuan.orange.Service.ProductOrderService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class ProductOrderController {
    @Resource
    ProductOrderService productOrderService;
    @PostMapping("/product/order")
    public R createProductOrder(@RequestBody ProductOrderModel order, HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        order.setBuyer_id(openid);
        return productOrderService.createProductOrder(order);
    }
    @GetMapping("/product/order")
    public R getProductOrderList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        return productOrderService.getProductOrderList(openid);
    }
    @GetMapping("/pluralist/order")
    public R getPluralistOrderList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        return productOrderService.getPluralistOrderList(openid);
    }
    @GetMapping("/product/apply")
    public R getProductApplyList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        return productOrderService.getProductApplyList(openid);
    }
    @GetMapping("/pluralist/apply")
    public R getPluralistApplyList(HttpServletRequest req) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        return productOrderService.getPluralistApplyList(openid);
    }
    @PutMapping("/product/apply/{product_id}")
    public R accessProductOrder(HttpServletRequest req, @PathVariable int product_id) {
        String openid = JWTUtils.getOpenid(req.getHeader("Authorization"));
        return productOrderService.accessProductOrder(openid,product_id);
    }
}
