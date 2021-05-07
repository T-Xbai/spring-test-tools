package com.shandiangou.springtesttools.services.sdop;

import com.shandiangou.springtesttools.dao.ItemOpenApiDao;
import com.tools.api.common.httpclient.ResponseUtils;


public interface ItemOpenApiService {

    ResponseUtils itemOpenApiReq(ItemOpenApiDao itemOpenApiDao);


}

