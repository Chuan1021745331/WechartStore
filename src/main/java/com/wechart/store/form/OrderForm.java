package com.wechart.store.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: OrderForm
 * @包名: com.wechart.store.form
 * @描述: 用于接收form表单数据，并验证数据
 * @日期: 2018/5/22 23:01
 */
@Data
public class OrderForm {
    /**
     * 买家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;

    /**
     * 买家手机号
     */
    @NotEmpty(message = "手机号必填")
    private String phone;

    /**
     * 买家地址
     */
    @NotEmpty(message = "地址必填")
    private String address;

    /**
     * 买家微信openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    /**
     * 购物车
     */
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
