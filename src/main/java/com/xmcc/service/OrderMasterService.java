package com.xmcc.service;

import com.xmcc.common.ResultResponse;

import com.xmcc.dto.OrderMasterDto;



public interface OrderMasterService {

     ResultResponse InsertOrder(OrderMasterDto orderMasterDto);
     ResultResponse findBybuyreOpenId(String openId,int page,int size);
     ResultResponse findBybuyreOpenIdAndOrderId(String openId, String orderId);
     ResultResponse cancelOrder(String openId, String orderId);
}
