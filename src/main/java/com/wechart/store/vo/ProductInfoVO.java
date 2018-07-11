package com.wechart.store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductInfo
 * @包名: com.wechart.store.vo
 * @描述: 商品详情
 * @日期: 2018/5/21 20:48
 */
@Data
public class ProductInfoVO {
    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    @JsonProperty("description")
    private String productDescription;

    @JsonProperty("icon")
    private String productIcon;
}
