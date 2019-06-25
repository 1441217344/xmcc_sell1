package com.xmcc.service.impl;


import com.xmcc.dao.impl.BatchDaoImpl;
import com.xmcc.entity.OrderDetail;
import com.xmcc.service.OrderDetailService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderDetailServicelImpl extends BatchDaoImpl<OrderDetail> implements OrderDetailService {
    @Override
    @Transactional//加入事务管理
    public void batchInsert(List<OrderDetail> list) {

        super.batchInsert(list);
    }
}
