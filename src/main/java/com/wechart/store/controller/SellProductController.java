package com.wechart.store.controller;

import com.wechart.store.model.ProductInfo;
import com.wechart.store.service.ProductService;
import com.wechart.store.utils.KeyUtil;
import com.wechart.store.utils.ResultVOUtil;
import com.wechart.store.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: SellProductController
 * @包名: com.wechart.store.controller
 * @描述: 卖家端
 * @日期: 2018/6/2 17:46
 */
@Controller
@RequestMapping("/product")
public class SellProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/save")
    @ResponseBody
    public ResultVO save(ProductInfo productInfo){
        ProductInfo info=new ProductInfo();
        if(!StringUtils.isEmpty(productInfo.getProductId())){
            info = productService.findProductById(productInfo.getProductId());
        }else{
            info.setProductId(KeyUtil.genUniqueKey());
        }
        BeanUtils.copyProperties(productInfo,info);
        ProductInfo saveResult = productService.save(productInfo);
        return ResultVOUtil.success(saveResult);
    }

    @RequestMapping("/decr/{productId}/{decrCount}")
    @ResponseBody
    public ResultVO decr(@PathVariable String productId, @PathVariable  Integer decrCount){
        boolean b = productService.decreaseStock(productId, decrCount);
        if(b){
            return ResultVOUtil.success("减库存成功");
        }
        return ResultVOUtil.error("失败了");
    }
}
