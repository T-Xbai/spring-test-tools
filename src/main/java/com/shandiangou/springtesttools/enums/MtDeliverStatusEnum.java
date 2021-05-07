package com.shandiangou.springtesttools.enums;

import lombok.Getter;

/**
 * @ClassName: MtDeliverStatusEnum
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 17:18
 * @Description:  美团配送单状态 枚举
 */
@Getter
public enum MtDeliverStatusEnum {

    ACCEPT(1,"接单"),

    PICK_UP(2,"取货"),

    DELIVER(3,"送达"),

    REARRANGE(4,"改派骑手"),

    CANCEL(5,"取消")



    ;


    private int code ;
    private String desc;

    public int getCode() {
        return code;
    }

    MtDeliverStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
