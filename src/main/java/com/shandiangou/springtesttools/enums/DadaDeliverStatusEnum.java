package com.shandiangou.springtesttools.enums;

import lombok.Getter;

/**
 * @ClassName: DadaDeliverStatusEnum
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-05 14:41
 * @Description:
 */
@Getter
public enum DadaDeliverStatusEnum {

    ACCEPT(0, "接单"),

    FETCH(1, "取货"),

    FINISH(2,"完成"),

    EXPIRE(3,"过期"),

    CANCEL(4,"取消"),

    TRANSPORTER_CANCEL(5,"骑手取消")
    ;
    private int code;
    private String desc;



    DadaDeliverStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
