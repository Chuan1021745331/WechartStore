package com.wechart.store.service.impl;

import com.wechart.store.dto.CartDTO;
import com.wechart.store.enums.ResultStatusEnum;
import com.wechart.store.exception.SellException;
import com.wechart.store.model.ProductInfo;
import com.wechart.store.repository.ProductInfoRepository;
import com.wechart.store.service.ProductService;
import com.wechart.store.service.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductServiceImpl
 * @包名: com.wechart.store.service.impl
 * @描述: 商品服务
 * @日期: 2018/5/20 22:22
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public ProductInfo save(ProductInfo productInfo) {

        ProductInfo saveResult = productInfoRepository.save(productInfo);
        return saveResult;
    }

    @Override
    public ProductInfo findProductById(String productId) {
        return productInfoRepository.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findAll() {
        return productInfoRepository.findAll();
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public boolean decreaseStock(String productId,Integer decrCount) {
        String lock = redisLock.lock(productId, 10000, 1000000);
        //没有获取锁,直接报错提示系统繁忙
        if(StringUtils.isEmpty(lock)){
            log.error("ProductService  save 无法获取锁 锁名:"+productId);
//            throw new SellException(ResultStatusEnum.SYSTEM_BUSY_ERROR);
            redisTemplate.opsForValue().increment("notGetLock",1);
            return false;
        }
        ProductInfo productInfo = productInfoRepository.getOne(productId);
        if(productInfo==null){
            new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
        }
        int residue = productInfo.getProductStock() - decrCount;
        if(residue<0){
            new SellException(ResultStatusEnum.PRODUCT_STOCK_ERROR);
        }
        productInfo.setProductStock(residue);
        productInfoRepository.save(productInfo);
        log.info("减库存成功 当前库存为:"+residue);
        //释放锁
        redisLock.unlock(productId,lock);
        return true;
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cart:cartDTOList){
            ProductInfo productInfo = productInfoRepository.getOne(cart.getProductId());
            if(productInfo==null){
                new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            int residue = productInfo.getProductStock() - cart.getProductQuantity();
            if(residue<0){
                new SellException(ResultStatusEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(residue);
            productInfoRepository.save(productInfo);
        }
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cart:cartDTOList){
            ProductInfo productInfo = productInfoRepository.getOne(cart.getProductId());
            if(productInfo==null){
                new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() + cart.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);
        }
    }
}
