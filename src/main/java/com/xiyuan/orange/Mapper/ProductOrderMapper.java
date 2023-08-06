package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.ProductOrderModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ProductOrderMapper {
    @Insert("INSERT INTO product_order(buyer_id,product_id) VALUES(#{buyer_id},#{product_id})")
    int createProductOrder(ProductOrderModel order);
    @Select("SELECT * FROM product_order WHERE product_id = #{product_id} AND buyer_id = #{buyer_id}")
    ProductOrderModel getProductOrderByProductIDAndBuyerID(ProductOrderModel order);
    @Select("SELECT p_o.*,p.content as product_content,p.price as product_price,u.nick_name as seller_name,u.avatar as seller_avatar,IF(p_o.status = 2,u.phone,null) as seller_phone FROM product_order p_o JOIN products p ON p.product_id = p_o.product_id JOIN users u ON u.openid = p.seller_id WHERE p_o.buyer_id = #{openid} and p.type != -1")
    List<ProductOrderModel> getProductOrderList(String openid);
    @Select("SELECT p_o.*,p.content as product_content,u.nick_name as buyer_name,u.avatar as buyer_avatar FROM (product_order p_o JOIN products p ON p.product_id = p_o.product_id) JOIN users u ON u.openid = p_o.buyer_id WHERE p.seller_id = #{openid} and p.type != -1")
    List<ProductOrderModel> getProductApplyList(String openid);
    @Update("UPDATE product_order SET status = 2 WHERE product_id = #{product_id}")
    int accessProductApply(int product_id);
//

    @Select("SELECT p_o.*,p.content as product_content,p.price as product_price,u.nick_name as seller_name,u.avatar as seller_avatar,IF(p_o.status = 2,u.phone,null) as seller_phone FROM product_order p_o JOIN products p ON p.product_id = p_o.product_id JOIN users u ON u.openid = p.seller_id WHERE p_o.buyer_id = #{openid} and p.type =-1")
    List<ProductOrderModel> getPluralistOrderList(String openid);
    @Select("SELECT p_o.*,p.content as product_content,u.nick_name as buyer_name,u.avatar as buyer_avatar FROM (product_order p_o JOIN products p ON p.product_id = p_o.product_id) JOIN users u ON u.openid = p_o.buyer_id WHERE p.seller_id = #{openid} and p.type = -1")
    List<ProductOrderModel> getPluralistApplyList(String openid);
}
