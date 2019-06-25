package com.xmcc.repository;

import com.xmcc.entity.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductInfoRepository extends JpaRepository<ProductInfo,String > {

    //根据类目的编号以及状态查询商品信息
    List<ProductInfo> findByProductStatusAndCategoryTypeIn(Integer status, List<Integer> typeList);


}
