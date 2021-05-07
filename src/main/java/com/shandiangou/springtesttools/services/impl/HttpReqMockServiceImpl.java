package com.shandiangou.springtesttools.services.impl;

import com.alibaba.fastjson.JSON;
import com.shandiangou.springtesttools.dao.HttpReqMockDao;
import com.shandiangou.springtesttools.services.HttpReqMockService;
import com.tools.api.common.httpclient.HttpReqUtils;
import com.tools.api.common.httpclient.ResponseUtils;
import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @ClassName: HttpReqMockServiceImpl
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-07 17:08
 * @Description:
 */
@Service
public class HttpReqMockServiceImpl implements HttpReqMockService {

    @Override
    public ResponseUtils doGet(HttpReqMockDao mockDao) {

        return new HttpReqUtils()
                .url(mockDao.getUrl())
                .doGet();

    }

    @Override
    public ResponseUtils doPost(HttpReqMockDao mockDao) {

        return new HttpReqUtils()
                .url(mockDao.getUrl())
                .body(JSON.parseObject(mockDao.getBody()))
                .doPost();
    }


    @Override
    public ResponseUtils doUpload(HttpReqMockDao mockDao) {
        String key_ = "";
        InputStream inputStream = null;
        for (String key : mockDao.getInputStreamMap().keySet()){
           inputStream =  mockDao.getInputStreamMap().get(key);
           key_ = key;
        }

        return new HttpReqUtils()
                .url(mockDao.getUrl())
                .addBinaryBody(key_,inputStream, ContentType.MULTIPART_FORM_DATA,key_)
                .upload();

    }

    @Override
    public ResponseUtils doPut(HttpReqMockDao mockDao) {

        return null;
    }

    @Override
    public ResponseUtils doDel(HttpReqMockDao mockDao) {
        return null;
    }

    @Override
    public ResponseUtils doOption(HttpReqMockDao mockDao) {
        return null;
    }

    @Override
    public ResponseUtils doReq(HttpReqMockDao mockDao) {

        switch (mockDao.getMethod().toUpperCase()) {
            case "POST":
                return this.doPost(mockDao);
            case "PUT":
                return this.doPut(mockDao);
            case "DELETE":
                return this.doDel(mockDao);
            case "OPTION":
                return this.doOption(mockDao);
            default:
                return this.doGet(mockDao);
        }
    }
}
