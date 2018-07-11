package com.wechart.store.service.impl;

import com.wechart.store.model.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() throws Exception {
        ProductCategory one = categoryService.findOne(1);
        Assert.assertEquals(new Integer(3),new Integer(one.getCategoryType()));
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> all = categoryService.findAll();
        Assert.assertNotEquals(new Integer(0),new Integer(all.size()));
    }

    @Test
    public void save() throws Exception {
        ProductCategory category = new ProductCategory();
        category.setCategoryType(2);
        category.setCategoryName("手机");
        ProductCategory save = categoryService.save(category);
        Assert.assertNotNull(save);
    }

    @Test
    public void findProductCategoryIn() throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        List<ProductCategory> byProductCategoryIn = categoryService.findByProductCategoryIn(list);
        log.info(byProductCategoryIn.size()+"");
        Assert.assertEquals(new Integer(0),new Integer(byProductCategoryIn.size()));
    }

    @Test
    public void getProductCategoryByName() throws Exception {
        List<ProductCategory> category = categoryService.getProductCategoryByName("手");
        log.info(category.toString());
        Assert.assertNotNull(category);
    }

}