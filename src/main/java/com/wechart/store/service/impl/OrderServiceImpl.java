package com.wechart.store.service.impl;

import com.wechart.store.converter.OrderMaster2OrderDTOConverter;
import com.wechart.store.dto.CartDTO;
import com.wechart.store.dto.OrderDTO;
import com.wechart.store.enums.OrderStatusEnum;
import com.wechart.store.enums.PayStatusEnum;
import com.wechart.store.enums.ResultStatusEnum;
import com.wechart.store.exception.SellException;
import com.wechart.store.model.OrderDetail;
import com.wechart.store.model.OrderMaster;
import com.wechart.store.model.ProductInfo;
import com.wechart.store.repository.OrderDetailRepository;
import com.wechart.store.repository.OrderMasterRepository;
import com.wechart.store.service.OrderService;
import com.wechart.store.service.ProductService;
import com.wechart.store.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: OrderServiceImpl
 * @包名: com.wechart.store.service.impl
 * @描述: (用一句话描述该文件做什么)
 * @日期: 2018/5/24 21:23
 */
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal totalPrice=new BigDecimal(BigInteger.ZERO);
        //1.计算总金额
        for (OrderDetail orderDetail: orderDTO.getOrderDetailList()) {
            ProductInfo product = productService.findProductById(orderDetail.getProductId());
            if(product==null){
                throw new SellException(ResultStatusEnum.PRODUCT_NOT_EXIST);
            }
            totalPrice= product.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(totalPrice);

            //2.补全订单详情数据入库
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            BeanUtils.copyProperties(product,orderDetail);
            orderDetailRepository.save(orderDetail);
        }
        //3.减库存
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);

        //4.存入订单
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster master = orderMasterRepository.save(orderMaster);
        return OrderMaster2OrderDTOConverter.convert(master);
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findById(orderId).orElseThrow(()->new SellException(ResultStatusEnum.ORDER_NOT_EXIST));
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultStatusEnum.CART_EMPTY);
        }
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenId(buyerOpenId, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());
        PageImpl<OrderDTO> orderDTOPage = new PageImpl<>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //1.判断订单状态
        if (!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
            throw new SellException(ResultStatusEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败, orderMaster={}", orderMaster);
            throw new SellException(ResultStatusEnum.ORDER_UPDATE_FAIL);
        }

        //返回库存
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品详情, orderDTO={}", orderDTO);
            throw new SellException(ResultStatusEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDTOList);

        //如果已支付, 需要退款
        if (orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
        }

        return orderDTO;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(Pageable pageable) {
        return null;
    }
}
