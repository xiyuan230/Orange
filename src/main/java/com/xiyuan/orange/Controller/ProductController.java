package com.xiyuan.orange.Controller;

import com.xiyuan.orange.Common.R;
import com.xiyuan.orange.Model.ProductModel;
import com.xiyuan.orange.Service.ProductService;
import com.xiyuan.orange.Utils.JWTUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Resource
    ProductService productService;

    @PostMapping("/product")
    public R createProduct(@RequestBody ProductModel productModel, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(token);
        productModel.setSeller_id(openid);
        System.out.println(productModel);
        if (productService.createProduct(productModel)) {
            return R.success().setMsg("发布成功");
        }
        return R.error("发布失败");

    }
    @GetMapping("/product")
    public R getProductListByType(@RequestParam("page") int page,@RequestParam("size") int size,@RequestParam(name = "type",required = false) Integer type){
        List<ProductModel> productList;
        if(type == null) {
            productList = productService.getProductList(page,size);
        } else {
            productList = productService.getProductListByType(page,size,type);
        }
        return R.success(productList).setMsg("获取商品列表成功");
    }
    @GetMapping("/product/school/{school_id}")
    public R getProductListBySchool(@RequestParam("page") int page, @RequestParam("size") int size, @PathVariable int school_id){
        List<ProductModel> productList = productService.getProductListBySchool(page,size,school_id);
        return R.success(productList).setMsg("获取商品列表成功");
    }
    @GetMapping("/product/search")
    public R getProductListBySearch(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("query") String query){
        List<ProductModel> productList = productService.getProductListBySearch(page,size,query);
        return R.success(productList).setMsg("获取商品列表成功");
    }
    @GetMapping("/product/{product_id}")
    public R getProductByProductID(@PathVariable int product_id) {
        ProductModel product = productService.getProductByProductID(product_id);
        return R.success(product).setMsg("获取商品详情成功");
    }
    @GetMapping("/product/type")
    public R getProductTypeList() {
        List<Map<Integer, String>> productTypeList = productService.getProductTypeList();
        return R.success(productTypeList).setMsg("获取商品分类列表成功");
    }
    @GetMapping("/product/publish")
    public R getProductListByOpenid(HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(authorization);
        List<ProductModel> productListByOpenid = productService.getProductListByOpenid(openid);
        return R.success(productListByOpenid).setMsg("获取商品列表成功");
    }

    @PutMapping("/product/publish")
    public R updateProductStatus(@RequestBody ProductModel product,HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(authorization);
        if (!productService.updateProductStatus(product,openid)){
            return R.error("更新失败");
        }
        return R.success().setMsg("更新成功");
    }
    @DeleteMapping("/product/publish/{product_id}")
    public R deleteProduct(@PathVariable int product_id,HttpServletRequest req) {
        String authorization = req.getHeader("Authorization");
        String openid = JWTUtils.getOpenid(authorization);
        if (!productService.deleteProduct(product_id,openid)){
            return R.error("删除失败");
        }
        return R.success().setMsg("删除成功");
    }
}
