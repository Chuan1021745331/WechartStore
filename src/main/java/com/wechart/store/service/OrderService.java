package com.wechart.store.service;

import com.wechart.store.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


/**
 * OrderMaster服务
 */
public interface OrderService {

    /** 创建订单. */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询一个订单 */
    OrderDTO findOne(String orderId);

    /** 根据买家查询订单列表 */
    Page<OrderDTO> findList(String buyerOpenId, Pageable pageable);

    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完成订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单. */
    OrderDTO paid(OrderDTO orderDTO);

    /** 查询订单列表. */
    Page<OrderDTO> findList(Pageable pageable);
}
