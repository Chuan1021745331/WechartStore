package com.wechart.store.service;

import com.wechart.store.model.ProductCategory;

import java.util.List;

public interface CategoryService {
    ProductCategory findOne(int id);
    List<ProductCategory> findAll();
    ProductCategory save(ProductCategory category);

    /**
     * 根据类型查找
     * @param list
     * @return
     */
    List<ProductCategory> findByProductCategoryIn(List<Integer> list);

    List<ProductCategory> getProductCategoryByName(String name);
}
