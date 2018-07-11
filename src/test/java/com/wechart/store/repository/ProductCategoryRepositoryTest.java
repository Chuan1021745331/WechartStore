package com.wechart.store.repository;

import com.wechart.store.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    public void save() throws Exception {
        ProductCategory category = new ProductCategory();
        category.setCategoryName("电脑");
        category.setCategoryType(3);
        ProductCategory result = categoryRepository.save(category);
        log.info(result.toString());

    }
}