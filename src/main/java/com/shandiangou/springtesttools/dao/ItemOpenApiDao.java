package com.shandiangou.springtesttools.dao;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @ClassName: ItemOpenApiDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2020-12-31 15:38
 * @Description:
 */
@Data
public class ItemOpenApiDao {

    public ItemOpenApiDao() {
    }

    @NonNull
    private String env;

    private String method;

    private String params;

    private String appId;

    private String appSecret;



}
