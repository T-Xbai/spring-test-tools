package com.shandiangou.springtesttools.enums;

/**
 * @ClassName: PlatformCodeEnum
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 18:02
 * @Description:
 */
public enum PlatformCodeEnum {

    DADA(5,"达达"),
    MT(14,"美团众包"),
    SD(0,"接单:商家自送"),
    ZS(99,"转单:商家自送"),
    ;

    PlatformCodeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
