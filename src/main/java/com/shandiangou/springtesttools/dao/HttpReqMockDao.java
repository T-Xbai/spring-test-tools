package com.shandiangou.springtesttools.dao;

import lombok.Data;

import java.io.InputStream;
import java.util.Map;

/**
 * @ClassName: HttpReqMockDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-07 17:01
 * @Description:
 */
@Data
public class HttpReqMockDao {


    private String url;

    private String params;

    private String method;

    private String headers;

    private String body;

    private Map<String,InputStream> inputStreamMap;


}
