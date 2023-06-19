package com.xiyuan.orange.Model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductModel {
    private int product_id;
    private String seller_id;
    private String content;
    private float price;
    private int status;
    private int type;
    private String type_name;
    private LocalDateTime create_time;
    private String seller_name;
    private String seller_avatar;
    private String seller_school;
    private List<ProductImageModel> image_list;
}
