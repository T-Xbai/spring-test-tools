package com.shandiangou.springtesttools.services;

import com.shandiangou.springtesttools.dao.HttpReqMockDao;
import com.tools.api.common.httpclient.ResponseUtils;

/**
 * @ClassName: HttpReqMockService
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-07 16:59
 * @Description:
 */
public interface HttpReqMockService {


    ResponseUtils doGet(HttpReqMockDao mockDao);

    ResponseUtils doPost(HttpReqMockDao mockDao);

    ResponseUtils doUpload(HttpReqMockDao mockDao);


    ResponseUtils doPut(HttpReqMockDao mockDao);

    ResponseUtils doDel(HttpReqMockDao mockDao);

    ResponseUtils doOption(HttpReqMockDao mockDao);

    ResponseUtils doReq(HttpReqMockDao mockDao);

}
