package com.wechart.store.repository;

import com.wechart.store.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);

    @Query(value = "select * from product_category where category_name like %?1%",nativeQuery=true)
    List<ProductCategory> getByCategoryName(String name);
}
