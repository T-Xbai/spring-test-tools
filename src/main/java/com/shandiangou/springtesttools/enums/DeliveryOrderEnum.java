package com.shandiangou.springtesttools.enums;

/**
 * @ClassName: DeliveryOrderEnum
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 18:01
 * @Description:
 */
public enum DeliveryOrderEnum {

    D_1(1,"待配送"),
    D_2(2,"在途中"),
    D_3(3,"已确认"),
    D_4(4,"退单"),
    D_5(5,"待抢单"),
    D_6(6,"拒绝签收"),
    D_7(7,"订单取消"),
    D_10(10,"转单"),


    ;
    private int code;
    private String msg;

    DeliveryOrderEnum(int code ,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
