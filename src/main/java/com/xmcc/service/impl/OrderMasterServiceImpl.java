package com.xmcc.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xmcc.common.*;
import com.xmcc.dto.OrderDetailDto;
import com.xmcc.dto.OrderMasterDto;
import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.entity.ProductInfo;
import com.xmcc.exception.CustomException;
import com.xmcc.repository.OrderMasterRepository;
import com.xmcc.service.OrderDetailService;
import com.xmcc.service.OrderMasterService;
import com.xmcc.service.ProductInfoService;
import com.xmcc.util.BigDecimalUtil;
import com.xmcc.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;


    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public ResultResponse InsertOrder(OrderMasterDto orderMasterDto) {

        //取出订单项
        List<OrderDetailDto> items = orderMasterDto.getItems();
        //创建集合来存贮Orderdetail
        List<OrderDetail> orderDetailList = Lists.newArrayList();
        //进行金额的初始化
        BigDecimal totalprice = new BigDecimal("0");

        //遍历订单项，获取商品详情
        for (OrderDetailDto orderDetailDto:items){

            ResultResponse<ProductInfo> resultResponse = productInfoService.queryById(orderDetailDto.getProductId());
            //判断ResultResponse的code即可
            if (resultResponse.getCode()== ResultEnums.FAIL.getCode()){
                throw  new CustomException(resultResponse.getMsg());
            }

            //得到商品
            ProductInfo productInfo = resultResponse.getData();
            //比较库存
            if (productInfo.getProductStock()<orderDetailDto.getProductQuantity()){
                throw new CustomException(ProductEnums.PRODUCT_NOT_ENOUGH.getMsg());
            }

            //创建订单项
            OrderDetail orderDetail = OrderDetail.builder().detailId(IDUtils.createIdbyUUID()).productIcon(productInfo.getProductIcon())
                    .productId(orderDetailDto.getProductId()).productName(productInfo.getProductName())
                    .productPrice(productInfo.getProductPrice()).productQuantity(orderDetailDto.getProductQuantity())
                    .build();
            orderDetailList.add(orderDetail);

            //修改对应商品的库存
            productInfo.setProductStock(productInfo.getProductStock()-orderDetailDto.getProductQuantity());
            productInfoService.updateProduct(productInfo);
            //计算价格

            totalprice= BigDecimalUtil.add(totalprice,BigDecimalUtil.multi(productInfo.getProductPrice(),orderDetail.getProductQuantity()));


        }
        //生成订单id
        String order_id = IDUtils.createIdbyUUID();
        //构建订单信息
        OrderMaster orderMaster = OrderMaster.builder().orderId(order_id).buyerAddress(orderMasterDto.getAddress()).
                buyerName(orderMasterDto.getName()).buyerOpenid(orderMasterDto.getOpenid())
                .buyerPhone(orderMasterDto.getPhone()).orderAmount(totalprice).orderStatus(OrderEnums.NEW.getCode())
                .payStatus(PayEnums.WAIT.getCode()).build();
        ////将订单id设置到订单项中
        List<OrderDetail> orderDetails = orderDetailList.stream().map(orderDetail ->
        {
            orderDetail.setOrderId(order_id);
            return orderDetail;
        }).collect(Collectors.toList());


        //批量插入订单项
        orderDetailService.batchInsert(orderDetailList);

        //插入订单
        orderMasterRepository.save(orderMaster);

        HashMap<String,String> map = Maps.newHashMap();
        map.put("orderId",order_id);



        return ResultResponse.success(map);
    }
}

