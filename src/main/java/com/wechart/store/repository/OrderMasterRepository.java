package com.wechart.store.repository;

import com.wechart.store.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String>{

    List<OrderMaster>  findByBuyerOpenId(String openId);

    Page<OrderMaster> findByBuyerOpenId(String openId, Pageable pageable);
}
