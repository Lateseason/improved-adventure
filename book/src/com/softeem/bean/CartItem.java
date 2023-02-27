package com.softeem.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Integer id ;//主键ID
    private String name;//商品名称
    private Integer count;//商品数量
    private BigDecimal price;//商品单价
    private BigDecimal totalPrice;//商品总价
}
