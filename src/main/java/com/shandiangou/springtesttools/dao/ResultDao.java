package com.shandiangou.springtesttools.dao;

import lombok.Data;

/**
 * @ClassName: ResultDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2020-12-31 15:34
 * @Description:
 */
@Data
public  class   ResultDao<T> {

    private Integer code;

    private String message;

    private T t;

    public  ResultDao(T t) {
        this.code = 0;
        this.message = "success";
        this.t = t;
    }

    public ResultDao(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResultDao(Integer code, String message, T t) {
        this.code = code;
        this.message = message;
        this.t = t;
    }
}

