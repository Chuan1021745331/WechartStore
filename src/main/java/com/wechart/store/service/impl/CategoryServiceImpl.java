package com.wechart.store.service.impl;

import com.wechart.store.model.ProductCategory;
import com.wechart.store.repository.CategoryRepository;
import com.wechart.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductServiceImpl
 * @包名: com.wechart.store.service.impl
 * @描述: (用一句话描述该文件做什么)
 * @日期: 2018/5/19 23:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public ProductCategory findOne(int id) {
        Optional<ProductCategory> byId = categoryRepository.findById(id);
        return byId.orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public ProductCategory save(ProductCategory category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<ProductCategory> findByProductCategoryIn(List<Integer> list) {
        return categoryRepository.findByCategoryTypeIn(list);
    }

    @Override
    public List<ProductCategory> getProductCategoryByName(String name) {
        return categoryRepository.getByCategoryName(name);
    }
}
