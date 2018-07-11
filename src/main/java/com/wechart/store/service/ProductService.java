package com.wechart.store.service;

import com.wechart.store.dto.CartDTO;
import com.wechart.store.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    /**
     * 存入商品，返回存入后的信息
     * @return
     */
    ProductInfo save(ProductInfo productInfo);

    ProductInfo findProductById(String productId);

    /**
     * 查找所有
     * @return
     */
    List<ProductInfo> findAll();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    /**
     * 根据购物车减库存
     * @param cartDTOList
     * @return
     */
    void decreaseStock(List<CartDTO> cartDTOList);

    /**
     * 加库存
     * @param cartDTOList
     * @return
     */
    void increaseStock(List<CartDTO> cartDTOList);

    boolean decreaseStock(String productId,Integer decrCount);


}
