package com.wechart.store.repository;

import com.wechart.store.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单详情DAO
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String>{
    List<OrderDetail> findByOrderId(String orderId);
}
