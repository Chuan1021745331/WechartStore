package com.wechart.store.model;

import com.wechart.store.enums.ProductStatusEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductInfo
 * @包名: com.wechart.store.model
 * @描述: 商品详情
 * @日期: 2018/5/20 14:02
 */
@Entity
@Data
public class ProductInfo {

    @Id
    private String productId;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 商品价格
     */
    private BigDecimal productPrice;
    /**
     * 商品描述
     */
    private String productDescription;
    /**
     * 图片地址
     */
    private String productIcon;
    /**
     * 库存
     */
    private Integer productStock;
    /**
     * 商品状态
     */
    private Integer productStatus= ProductStatusEnum.UP.getCode();
    /**
     * 商品类目
     */
    private Integer categoryType;
    private Date createTime;
    private Date updateTime;
}
