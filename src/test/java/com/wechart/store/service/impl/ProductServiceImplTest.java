package com.wechart.store.service.impl;

import com.wechart.store.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    public void save() throws Exception {
    }

    @Test
    public void getProductById() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        Pageable pageable = PageRequest.of(1, 2);
        Page<ProductInfo> all = productService.findAll(pageable);
        Assert.assertNotNull(all);
    }

    @Test
    public void findAll1() throws Exception {

        List<ProductInfo> all = productService.findAll();
        Assert.assertNotNull(all);
    }

}