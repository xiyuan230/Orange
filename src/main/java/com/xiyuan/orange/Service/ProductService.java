package com.xiyuan.orange.Service;

import com.xiyuan.orange.Mapper.ProductImageMapper;
import com.xiyuan.orange.Mapper.ProductMapper;
import com.xiyuan.orange.Mapper.ProductTypeMapper;
import com.xiyuan.orange.Model.ImageModel;
import com.xiyuan.orange.Model.ProductImageModel;
import com.xiyuan.orange.Model.ProductModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    @Resource
    ProductMapper productMapper;
    @Resource
    ProductImageMapper productImageMapper;
    @Resource
    ProductTypeMapper productTypeMapper;
    public boolean createProduct(ProductModel productModel) {
        productModel.setCreate_time(LocalDateTime.now());
        if (productMapper.createProduct(productModel) == 0) {
            return false;
        }
        for (ProductImageModel image : productModel.getImage_list()) {
            image.setProduct_id(productModel.getProduct_id());
            productImageMapper.createImage(image);
        }
        return true;
    }
    public List<ProductModel> getProductList(int page,int size) {
        List<ProductModel> productList = productMapper.getProduct((page - 1) * size, size);
        for (ProductModel item : productList) {
            List<ProductImageModel> productImagesByProductID = productImageMapper.getProductImagesByProductID(item.getProduct_id());
            item.setImage_list(productImagesByProductID);
        }
        return productList;
    }
    public List<ProductModel> getProductListByType(int page,int size,int type) {
        List<ProductModel> productList = productMapper.getProductListByType((page - 1) * size, size,type);
        for (ProductModel item : productList) {
            List<ProductImageModel> productImagesByProductID = productImageMapper.getProductImagesByProductID(item.getProduct_id());
            item.setImage_list(productImagesByProductID);
        }
        return productList;
    }
    public List<ProductModel> getProductListBySchool(int page,int size,int school) {
        List<ProductModel> productList = productMapper.getProductListBySchool((page - 1) * size, size,school);
        for (ProductModel item : productList) {
            List<ProductImageModel> productImagesByProductID = productImageMapper.getProductImagesByProductID(item.getProduct_id());
            item.setImage_list(productImagesByProductID);
        }
        return productList;
    }
    public List<ProductModel> getProductListBySearch(int page,int size,String query) {
        List<ProductModel> productList = productMapper.getProductListBySearch((page - 1) * size, size,query);
        for (ProductModel item : productList) {
            List<ProductImageModel> productImagesByProductID = productImageMapper.getProductImagesByProductID(item.getProduct_id());
            item.setImage_list(productImagesByProductID);
        }
        return productList;
    }
    public ProductModel getProductByProductID(int product_id) {
        ProductModel product = productMapper.getProductByProductID(product_id);
        List<ProductImageModel> productImagesList = productImageMapper.getProductImagesByProductID(product_id);
        product.setImage_list(productImagesList);
        return product;
    }

    public List<ProductModel> getProductListByOpenid(String openid) {
        List<ProductModel> productList = productMapper.getProductListByOpenid(openid);
        for (ProductModel item : productList) {
            List<ProductImageModel> productImagesByProductID = productImageMapper.getProductImagesByProductID(item.getProduct_id());
            item.setImage_list(productImagesByProductID);
        }
        return productList;
    }

    public List<Map<Integer,String>> getProductTypeList() {
        List<Map<Integer, String>> productTypeList = productTypeMapper.getProductTypeList();
        return productTypeList;
    }

    public boolean updateProductStatus(ProductModel product,String openid){
        ProductModel productModel = productMapper.getProductByProductID(product.getProduct_id());
        if (!productModel.getSeller_id().equals(openid)) {
            return false;
        }
        if (productMapper.updateProductStatus(product) < 1) {
            return false;
        }
        return true;
    }
    public boolean deleteProduct(int product_id,String openid) {
        ProductModel productModel = productMapper.getProductByProductID(product_id);
        if (!productModel.getSeller_id().equals(openid)) {
            return false;
        }
        if (productMapper.deleteProduct(product_id) < 1) {
            return false;
        }
        return true;
    }
}
