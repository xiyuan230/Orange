package com.xiyuan.orange.Model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductOrderModel {
    private int order_id;
    private String buyer_id;
    private String seller_name;
    private String seller_avatar;
    private String seller_phone;
    private String buyer_name;
    private String buyer_avatar;
    private String buyer_phone;
    private int product_id;
    private float product_price;
    private String product_content;
    private String product_image;
    private int status;
    private LocalDateTime create_time;
}
