package com.wechart.store.model;

import com.wechart.store.enums.OrderStatusEnum;
import com.wechart.store.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: OrderMaster
 * @包名: com.wechart.store.model
 * @描述: 订单持久化对象
 * @日期: 2018/5/22 23:05
 */
@Entity
@Data
@DynamicUpdate
public class OrderMaster {
    @Id
    private String orderId;

    /** 买家姓名. */
    private String buyerName;

    /** 买家手机. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenId;

    /** 总金额. */
    private BigDecimal orderAmount;

    /** 订单状态 默认为新下. */
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /** 支付状态 默认未支付. */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /** 创建时间. */
    private Date createTime;

    /** 修改时间. */
    private Date updateTime;

}
