package com.shandiangou.springtesttools.page.deliver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tools.api.common.dao.config.DeliveryConfigDao;
import com.tools.api.common.httpclient.HttpConnect;
import com.tools.api.common.httpclient.ResponseUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @ClassName: DadaDeliverPathPage
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-05 14:27
 * @Description:
 */
public class DadaDeliverPathPage {

    private static final String url = DeliveryConfigDao.DADA_ONLINE_HOST
            .concat("/inner/api/gateWay/request");


    private String APP_KEY;
    private String APP_SECRET;
    private String SOURCE_ID;

    public DadaDeliverPathPage(String APP_KEY, String APP_SECRET, String SOURCE_ID) {
        this.APP_KEY = APP_KEY;
        this.APP_SECRET = APP_SECRET;
        this.SOURCE_ID = SOURCE_ID;
    }

    public DadaDeliverPathPage() {
        this.APP_KEY = DeliveryConfigDao.DADA_APP_KEY;
        this.APP_SECRET = DeliveryConfigDao.DADA_APP_SECRET;
        this.SOURCE_ID = DeliveryConfigDao.DADA_SOURCE_ID;

    }

    /**
     * 骑手接单
     */
    public ResponseUtils orderAccept(String orderId) {
        String path = "/api/order/accept";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        return dadaReq(path, bodyJson);
    }

    /**
     * 骑手取货
     */
    public ResponseUtils orderFetch(String orderId) {
        String path = "/api/order/fetch";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        return dadaReq(path, bodyJson);
    }


    /**
     * 模拟完成
     */
    public ResponseUtils orderFinish(String orderId) {
        String path = "/api/order/finish";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        return dadaReq(path, bodyJson);
    }

    /**
     * 模拟过期
     */
    public ResponseUtils orderExpire(String orderId) {
        String path = "/api/order/expire";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        return dadaReq(path, bodyJson);
    }


    /**
     * 模拟取消
     */
    public ResponseUtils orderCancel(String orderId, String reason) {
        String path = "/api/order/cancel";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        bodyJson.put("reason", reason);
        return dadaReq(path, bodyJson);
    }


    /**
     * 模拟骑士取消订单
     */
    public ResponseUtils transporterCancelOrder(String orderId, String notifyUrl) {
        String path = "/api/order/transporter/cancelOrder/mock";
        JSONObject bodyJson = new JSONObject();
        bodyJson.put("order_id", orderId);
        bodyJson.put("notify_url", notifyUrl);
        return dadaReq(path, bodyJson);
    }


    /**
     * 达达配送公共请求方法
     */
    private ResponseUtils dadaReq(String path, JSONObject body) {
        JSONObject reqBody = new JSONObject();
        reqBody.put("requestParams", this.getRequestParams(body));
        reqBody.put("requestUrl", DeliveryConfigDao.DADA_QA_HOST.concat(path));
        return HttpConnect.doPost(url, reqBody.toJSONString());

    }


    private String getRequestParams(JSONObject param) {
        Map<String, String> requestParams = new HashMap();
        requestParams.put("source_id", this.SOURCE_ID);
        requestParams.put("app_key", this.APP_KEY);
        requestParams.put("timestamp", String.valueOf(System.currentTimeMillis()));
        requestParams.put("format", "json");
        requestParams.put("v", "1.0");
        requestParams.put("body", param.toJSONString());
        requestParams.put("signature", this.getSign(requestParams, this.APP_SECRET));
        return JSON.toJSONString(requestParams);
    }


    private String getSign(Map<String, String> requestParams, String appSecret) {
        Map<String, String> sortedParams = new TreeMap(requestParams);
        Set<Map.Entry<String, String>> entrySets = sortedParams.entrySet();
        StringBuilder signStr = new StringBuilder();

        entrySets.forEach(entry -> signStr.append(entry.getKey()).append(entry.getValue()));

        String toSign = appSecret.concat(signStr.toString()).concat(appSecret);
        String sign = this.encrypt(toSign);
        return sign.toUpperCase();
    }

    private String encrypt(String inbuf) {
        String s = null;
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inbuf.getBytes(StandardCharsets.UTF_8));
            byte[] tmp = md.digest();
            char[] str = new char[32];
            int k = 0;

            for (int i = 0; i < 16; ++i) {
                byte byte0 = tmp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 15];
                str[k++] = hexDigits[byte0 & 15];
            }

            s = new String(str);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return s;
    }

}
