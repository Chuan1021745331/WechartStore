package com.wechart.store.model;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *
 * @类名: ProductCategory
 * @包名: com.wechart.store.model
 * @描述: (用一句话描述该文件做什么)
 * @日期: 2018/5/19 13:22
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {
    /**
     * 类目id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    /**
     * 类型
     */
    private int categoryType;
    /**
     * 类名
     */
    private String categoryName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}
