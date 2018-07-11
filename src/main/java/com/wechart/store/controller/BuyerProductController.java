package com.wechart.store.controller;

import com.wechart.store.model.ProductCategory;
import com.wechart.store.model.ProductInfo;
import com.wechart.store.service.CategoryService;
import com.wechart.store.service.ProductService;
import com.wechart.store.utils.ResultVOUtil;
import com.wechart.store.vo.ProductInfoVO;
import com.wechart.store.vo.ProductVO;
import com.wechart.store.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ProductController
 * @包名: com.wechart.store.controller
 * @描述: (用一句话描述该文件做什么)
 * @日期: 2018/5/21 20:56
 */
@Controller
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    /**
     * 返回所有的商品，生成相应的数据格式
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public ResultVO list(){
        List<ProductInfo> productInfoList = productService.findAll();
        //lumbda表达式
        List<Integer> categoryList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .distinct()
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByProductCategoryIn(categoryList);
        //拼装数据
        List<ProductVO> productVOList=new ArrayList<>();
        productCategoryList.forEach(e->{
            //信息
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(e.getCategoryType());
            productVO.setCategoryName(e.getCategoryName());

            List<ProductInfoVO> productInfoVOS = new ArrayList<>();
            productInfoList.forEach(o->{
                if(e.getCategoryType()==o.getCategoryType()){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(o,productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            });
            productVO.setProductInfoVOList(productInfoVOS);
            productVOList.add(productVO);
        });
        return ResultVOUtil.success(productVOList);
    }
}
