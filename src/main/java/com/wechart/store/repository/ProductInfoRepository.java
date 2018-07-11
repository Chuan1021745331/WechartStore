package com.wechart.store.repository;

import com.wechart.store.model.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 商品信息DAO
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

}
