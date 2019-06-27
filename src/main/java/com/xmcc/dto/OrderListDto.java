package com.xmcc.dto;




import com.fasterxml.jackson.annotation.JsonProperty;
import com.xmcc.common.OrderEnums;
import com.xmcc.common.PayEnums;

import com.xmcc.entity.OrderDetail;
import com.xmcc.entity.OrderMaster;
import com.xmcc.entity.ProductCategory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.BeanUtils;


import javax.validation.Valid;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;

import java.util.List;


@Data
@ApiModel("订单列表实体类")  //swagger 参数描述信息
@Builder
public class OrderListDto implements Serializable{



    @JsonProperty("orderDetailList")
    @ApiModelProperty(value = "订单项集合",dataType = "List")
    private List<OrderDetail> orderDetailList ;

    private String orderId;

    /** 买家名字. */
    private String buyerName;

    /** 买家手机号. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信Openid. */
    private String buyerOpenid;

    /** 订单总金额. */
    private BigDecimal orderAmount;

    /** 订单状态, 默认为0新下单. */
    private Integer orderStatus = OrderEnums.NEW.getCode();

    /** 支付状态, 默认为0未支付. */
    private Integer payStatus = PayEnums.WAIT.getCode();

    /** 创建时间. */
    private String createTime;

    /** 更新时间. */
    private String updateTime;










    }



