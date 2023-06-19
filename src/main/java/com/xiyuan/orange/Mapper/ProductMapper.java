package com.xiyuan.orange.Mapper;

import com.xiyuan.orange.Model.ProductModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Insert("INSERT INTO products(seller_id,content,price,type,create_time) VALUES(#{seller_id},#{content},#{price},#{type},#{create_time})")
    @Options(useGeneratedKeys = true,keyProperty = "product_id",keyColumn = "product_id")
    int createProduct(ProductModel product);
    @Delete("DELETE FROM products WHERE product_id = #{product_id}")
    int deleteProduct(int product_id);
    @Update("UPDATE products SET status = #{status} WHERE product_id = #{product_id}")
    int updateProductStatus(ProductModel product);
    @Select("SELECT p.*,p_t.type_name as type_name,u.avatar as seller_avatar,u.nick_name as seller_name,s.school_name as seller_school FROM (((products p JOIN users u ON p.seller_id = u.openid) JOIN user_detail u_d ON u.openid = u_d.openid) JOIN schools s ON s.id = u_d.school_id) JOIN product_type p_t ON p.type = p_t.type_id WHERE p.status = 1 ORDER BY p.create_time DESC LIMIT #{offset},#{size}")
    List<ProductModel> getProduct(@Param("offset") int offset, @Param("size") int size);
    @Select("SELECT p.*,p_t.type_name as type_name,u.avatar as seller_avatar,u.nick_name as seller_name,s.school_name as seller_school FROM (((products p JOIN users u ON p.seller_id = u.openid) JOIN user_detail u_d ON u.openid = u_d.openid) JOIN schools s ON s.id = u_d.school_id) JOIN product_type p_t ON p.type = p_t.type_id WHERE p.status = 1 AND p.type = #{type_id} ORDER BY p.create_time DESC LIMIT #{offset},#{size}")
    List<ProductModel> getProductListByType(@Param("offset") int offset, @Param("size") int size, @Param("type_id") int type_id);
    @Select("SELECT p.*,p_t.type_name as type_name,u.avatar as seller_avatar,u.nick_name as seller_name,s.school_name as seller_school FROM (((products p JOIN users u ON p.seller_id = u.openid) JOIN user_detail u_d ON u.openid = u_d.openid) JOIN schools s ON s.id = u_d.school_id) JOIN product_type p_t ON p.type = p_t.type_id WHERE p.status = 1 AND u_d.school_id = #{school_id} ORDER BY p.create_time DESC LIMIT #{offset},#{size}")
    List<ProductModel> getProductListBySchool(@Param("offset") int offset,@Param("size") int size,@Param("school_id") int school_id);
    @Select("SELECT p.*,p_t.type_name as type_name,u.avatar as seller_avatar,u.nick_name as seller_name,s.school_name as seller_school FROM (((products p JOIN users u ON p.seller_id = u.openid) JOIN user_detail u_d ON u.openid = u_d.openid) JOIN schools s ON s.id = u_d.school_id) JOIN product_type p_t ON p.type = p_t.type_id WHERE p.status = 1 AND p.content LIKE CONCAT('%',#{query},'%') ORDER BY p.create_time DESC LIMIT #{offset},#{size}")
    List<ProductModel> getProductListBySearch(@Param("offset") int offset,@Param("size") int size,@Param("query") String query);
    @Select("SELECT p.*,p_t.type_name as type_name,u.avatar as seller_avatar,u.nick_name as seller_name,s.school_name as seller_school FROM (((products p JOIN users u ON p.seller_id = u.openid) JOIN user_detail u_d ON u.openid = u_d.openid) JOIN schools s ON s.id = u_d.school_id) JOIN product_type p_t ON p.type = p_t.type_id WHERE p.product_id = #{product_id}")
    ProductModel getProductByProductID(int product_id);
    @Select("SELECT p.* FROM products p WHERE p.seller_id = #{openid} ORDER BY p.create_time DESC")
    List<ProductModel> getProductListByOpenid(String openid);
}
