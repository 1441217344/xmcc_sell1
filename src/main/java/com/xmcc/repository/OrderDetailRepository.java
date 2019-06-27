package com.xmcc.repository;

import com.xmcc.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

List<OrderDetail>  findOrderDetailsByOrderIdIn(List<String> orderId);
List<OrderDetail>  findOrderDetailByOrderId(String orderId);

}
