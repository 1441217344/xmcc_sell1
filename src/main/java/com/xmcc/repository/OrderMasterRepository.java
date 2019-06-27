package com.xmcc.repository;

import com.xmcc.entity.OrderMaster;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderMasterRepository  extends JpaRepository<OrderMaster,String> {



    List<OrderMaster> findOrderMastersByBuyerOpenid(String openid, Pageable pageable);
    OrderMaster findOrderMastersByBuyerOpenidAndOrderId(String openid,String orderId);


    @Modifying
    @Query(value = "update order_master set order_status = ?1  where buyer_openid = ?2 and order_id = ?3",nativeQuery = true)
    void updateByopenIdAndOrderId(int status, String openId, String orderId);

}
