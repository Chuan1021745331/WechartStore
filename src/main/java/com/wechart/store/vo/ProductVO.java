package com.wechart.store.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wechart.store.model.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductDto
 * @包名: com.wechart.store.dto
 * @描述: 返回到的前台数据
 * @日期: 2018/5/21 20:40
 */
@Data
public class ProductVO {
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
