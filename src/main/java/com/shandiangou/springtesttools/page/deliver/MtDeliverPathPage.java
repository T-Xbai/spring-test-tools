package com.shandiangou.springtesttools.page.deliver;

import com.sankuai.meituan.banma.constants.CancelOrderReasonId;
import com.sankuai.meituan.banma.constants.RequestConstant;
import com.sankuai.meituan.banma.request.CancelOrderRequest;
import com.sankuai.meituan.banma.request.MockOrderRequest;
import com.sankuai.meituan.banma.util.DateUtil;
import com.sankuai.meituan.banma.util.ParamBuilder;
import com.sankuai.meituan.banma.util.SHA1Util;
import com.shandiangou.springtesttools.dao.MtDeliverMockDao;
import com.tools.api.common.httpclient.HttpConnect;
import com.tools.api.common.httpclient.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * @ClassName: MtDeliverPathPage
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 17:02
 * @Description: 美团接口 基础类
 */
public class MtDeliverPathPage {


    private static final Log logger = LogFactory.getLog(MtDeliverPathPage.class);
    private static String HOST = "https://peisongopen.meituan.com";
    private String APP_KEY ;
    private String APP_SECRET ;

    public MtDeliverPathPage(String APP_KEY, String APP_SECRET) {
        this.APP_KEY = APP_KEY;
        this.APP_SECRET = APP_SECRET;
    }


    // 模拟美团配送接单：mockAcceptOrder
    public  ResponseUtils mockAcceptOrder(Long testDeliveryId, String testMtPeisongId,MtDeliverMockDao mockDao ) {
        String url = HOST.concat("/api/test/order/arrange");
        return HttpConnect.doPost(url, buildMockRequest(testDeliveryId, testMtPeisongId,mockDao));
    }

    // 模拟美团配送取货 : mockPickUpOrder
    public  ResponseUtils mockPickUpOrder( Long testDeliveryId, String testMtPeisongId,MtDeliverMockDao mockDao) {
        String url = HOST.concat("/api/test/order/pickup");
        return HttpConnect.doPost(url, buildMockRequest(testDeliveryId, testMtPeisongId,mockDao));
    }



    // 模拟美团配送送达 : mockDeliverOrder
    public  ResponseUtils mockDeliverOrder(Long testDeliveryId, String testMtPeisongId,MtDeliverMockDao mockDao) {
        String url = HOST.concat("/api/test/order/deliver");
        return HttpConnect.doPost(url, buildMockRequest(testDeliveryId, testMtPeisongId,mockDao));
    }



    // 模拟美团配送改派骑手 : mockRearrangeOrder
    public  ResponseUtils mockRearrangeOrder(Long testDeliveryId, String testMtPeisongId,MtDeliverMockDao mockDao) {
        String url = HOST.concat("/api/test/order/rearrange");
        return HttpConnect.doPost(url, buildMockRequest(testDeliveryId, testMtPeisongId,mockDao));
    }


    // 模拟美团配送骑手取消 : mockCancelOrder
    public  ResponseUtils mockCancelOrder(Long testDeliveryId, String testMtPeisongId) {
        return HttpConnect.doPost(RequestConstant.ORDER_CANCEL, buildMockRequest(testDeliveryId, testMtPeisongId));
    }


    private  Map<String, String> buildMockRequest(Long testDeliveryId, String testMtPeisongId) {
        CancelOrderRequest request = new CancelOrderRequest();
        request.setAppkey(this.APP_KEY);
        request.setTimestamp(DateUtil.unixTime());
        request.setVersion("1.0");
        request.setDeliveryId(testDeliveryId);
        request.setMtPeisongId(testMtPeisongId);
        request.setCancelOrderReasonId(CancelOrderReasonId.PARTNER_REASON);
        request.setCancelReason("测试取消");
        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = generateSign(params, this.APP_SECRET);
        params.put("sign", sign);
        return params;
    }

    private  Map<String, String> buildMockRequest(Long deliverId,String mtPeisongId,MtDeliverMockDao mockDao) {
        MockOrderRequest request = new MockOrderRequest();
        request.setOrderId(mockDao.getOrderId());
        request.setAppkey(mockDao.getAppKey());
        request.setTimestamp((long) DateUtil.unixTime());
        request.setVersion("1.0");
        request.setDeliveryId(deliverId);
        request.setMtPeisongId(mtPeisongId);
        Map<String, String> params = ParamBuilder.convertToMap(request);
        String sign = generateSign(params, mockDao.getAppSecret());
        params.put("sign", sign);
        return params;
    }

    private  String generateSign(Map<String, String> params, String secret) {
        String encodeString = getEncodeString(params, secret);
        logger.info(String.format("encodeString: %s", encodeString));
        String sign = generateSign(encodeString);
        logger.info(String.format("generateSign: %s", sign));
        return sign;
    }

    private  String getEncodeString(Map<String, String> params, String secret) {
        Iterator<String> keyIter = params.keySet().iterator();
        TreeSet sortedParams = new TreeSet();

        while (keyIter.hasNext()) {
            sortedParams.add(keyIter.next());
        }

        StringBuilder strB = new StringBuilder(secret);
        Iterator var5 = sortedParams.iterator();

        while (var5.hasNext()) {
            String key = (String) var5.next();
            if (!key.equals("sign")) {
                String value = params.get(key);
                if (StringUtils.isNotEmpty(value)) {
                    strB.append(key).append(value);
                }
            }
        }

        return strB.toString();
    }

    private  String generateSign(String content) {
        String res = null;
        try {
            res = SHA1Util.Sha1(content).toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }





}
