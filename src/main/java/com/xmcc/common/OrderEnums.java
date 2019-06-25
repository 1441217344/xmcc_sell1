package com.xmcc.common;

import lombok.Getter;

@Getter
public enum OrderEnums {

    NEW(0,"新建订单"),
    FINSH(1,"已完成订单"),
    CANCEL(2,"已取消");


    private int code;
    private String msg;
    OrderEnums(int code,String msg){

        this.code = code;
        this.msg = msg;


    }
}
