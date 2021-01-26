package com.benz.redis.api.model;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Product implements Serializable {

    private int productId;
    private String productName;
    private int qty;
    private double price;
}
