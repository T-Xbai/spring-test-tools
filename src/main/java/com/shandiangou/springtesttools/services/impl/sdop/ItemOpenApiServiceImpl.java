package com.shandiangou.springtesttools.services.impl.sdop;

import com.shandiangou.springtesttools.common.EnvCommon;
import com.shandiangou.springtesttools.dao.ItemOpenApiDao;
import com.shandiangou.springtesttools.services.sdop.ItemOpenApiService;
import com.tools.api.common.httpclient.HttpConnect;
import com.tools.api.common.httpclient.ResponseUtils;
import com.tools.api.common.utils.SignUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: ItemOpenApiServiceImpl
 * @CreateUser: wangxiaohao
 * @CreateDate: 2020-12-31 15:28
 * @Description:
 */

@Service
public class ItemOpenApiServiceImpl implements ItemOpenApiService {

    private String path ="/router/rest";


    @Override
    public ResponseUtils itemOpenApiReq(ItemOpenApiDao itemOpenApiDao) {
            final String url = EnvCommon.setEnv(itemOpenApiDao.getEnv()).concat(path);
            return HttpConnect.doPost(url, formMap(itemOpenApiDao));
    }


    /**
     * 商品 open api 参数组装
     */
    private static Map<String, Object> formMap(ItemOpenApiDao itemOpenApiDao) {
        Map<String, Object> formMap = new HashMap<>();
        formMap.put("appId", itemOpenApiDao.getAppId());
        formMap.put("datetime", "2021-12-12 22:20:56");
        formMap.put("format", "json");
        formMap.put("method", itemOpenApiDao.getMethod());
        formMap.put("param", itemOpenApiDao.getParams());
        formMap.put("signMethod", "md5");
        String sign = SignUtils.signRequest(formMap, itemOpenApiDao.getAppSecret());
        formMap.put("sign", sign);
        return formMap;
    }
}
