package com.xiyuan.orange.Service;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Mapper.ProductMapper;
import com.xiyuan.orange.Mapper.ProductOrderMapper;
import com.xiyuan.orange.Model.ProductModel;
import com.xiyuan.orange.Model.ProductOrderModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductOrderService {
    @Resource
    ProductOrderMapper productOrderMapper;
    @Resource
    ProductMapper productMapper;

    public R createProductOrder(ProductOrderModel order) {
        ProductModel productModel = productMapper.getProductByProductID(order.getProduct_id());
        if (productModel.getSeller_id().equals(order.getBuyer_id())) {
            return R.error("无法购买自己的商品");
        }
        ProductOrderModel orderModel = productOrderMapper.getProductOrderByProductIDAndBuyerID(order);
        if (orderModel != null) {
            return R.error("已申请，等待通过");
        }
        if (productOrderMapper.createProductOrder(order)>0) {
            return R.success().setMsg("申请成功");
        }
        return R.error("申请失败");
    }

    public R getProductOrderList(String openid) {
        return R.success(productOrderMapper.getProductOrderList(openid)).setMsg("获取订单列表成功");
    }
    public R getProductApplyList(String openid) {
        return R.success(productOrderMapper.getProductApplyList(openid)).setMsg("获取申请列表成功");
    }
    public R accessProductOrder(String openid,int product_id) {
        ProductModel productModel = productMapper.getProductByProductID(product_id);
        if (!productModel.getSeller_id().equals(openid)) {
            return R.error("访问禁止").setCode(403);
        }
        if (productOrderMapper.accessProductApply(product_id) < 1) {
            return R.error("通过申请失败");
        }
        return R.success().setMsg("通过申请成功");
    }
}
