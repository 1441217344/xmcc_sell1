package com.xmcc.service;

import com.xmcc.common.ResultResponse;
import com.xmcc.dto.OrderMasterDto;
import com.xmcc.entity.OrderMaster;

public interface OrderMasterService {

     ResultResponse InsertOrder(OrderMasterDto orderMasterDto);



}
