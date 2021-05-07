package com.shandiangou.springtesttools.db.logistics;

import com.alibaba.fastjson.JSONObject;
import com.shandiangou.springtesttools.db.MysqlPage;
import com.shandiangou.springtesttools.enums.DeliveryOrderEnum;
import com.shandiangou.springtesttools.enums.PlatformCodeEnum;
import com.tools.api.common.utils.CommonUtils;
import com.tools.api.common.utils.MysqlConnectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: CourierOrderDB
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 17:59
 * @Description:
 */
public class CourierOrderDB extends MysqlPage {


    // 获取美团配送ID : getMtDeliverId
    public static JSONObject getMtDeliverId(String orderId) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderId", orderId);

        String sql = spliceSql(
                "courier_order", filterMap, "id", "json_extract(`extraInfo`,'$.thirdReturnOrderId') as thirdReturnOrderId"
        );


        MysqlConnectionUtils logisticsCon = poi("logistics");
        JSONObject queryRes = logisticsCon.firstQuery(sql);
        if (queryRes.size() > 0) {
            String thirdReturnOrderId = queryRes.getString("thirdReturnOrderId").replace("\"", "");
            queryRes.put("thirdReturnOrderId", thirdReturnOrderId);
        }
        logisticsCon.close();
        return queryRes;
    }


    public static JSONObject getShopNextReason(String orderId) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderId", orderId);

        String sql = spliceSql("courier_order", filterMap, "json_extract(`extraInfo`,'$.shopNextReason') as 'shopNextReason'");

        MysqlConnectionUtils logisticsCon = poi("logistics");
        JSONObject jsonObject = logisticsCon.firstQuery(sql);
        if (jsonObject.size() > 0) {
            String thirdReturnOrderId = jsonObject.getString("shopNextReason").replace("\"", "");
            jsonObject.put("shopNextReason", thirdReturnOrderId);
        }
        logisticsCon.close();
        return jsonObject;
    }

     // "校验配送状态是否存在 : cycleIsContainStatus
    public static boolean cycleIsContainStatus(String orderId, DeliveryOrderEnum deliveryOrderEnum, PlatformCodeEnum platformCodeEnum, int cycle) {


        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderId", orderId);
        filterMap.put("orderStatus", deliveryOrderEnum.getCode());
        filterMap.put("platform", platformCodeEnum.getCode());
        String sql = spliceSql("courier_order", filterMap);
        MysqlConnectionUtils logisticsCon = poi("logistics");
        try {
            for (int i = 0; i < cycle; i++) {
                List<JSONObject> queryListRes = logisticsCon.query(sql);
                if (queryListRes.size() > 0) {
                    return true;
                }
                CommonUtils.sleep(2000);
            }
        } finally {
            logisticsCon.close();
        }

        return false;
    }


    // 是否可以继续操作:isNeedShopNext
    public static boolean isNeedShopNext(String orderId,  PlatformCodeEnum platformCodeEnum,int cycle) {
        Map<String, Object> filterMap = new HashMap<>();
        filterMap.put("orderId", orderId);
        filterMap.put("platform", platformCodeEnum.getCode());
        String sql = spliceSql("courier_order", filterMap,"json_extract(`extraInfo`,'$.needShopNext') as 'needShopNext'");

        MysqlConnectionUtils logisticsCon = poi("logistics");

        try {
            for (int i = 0; i <cycle ; i++) {
                JSONObject jsonObject = logisticsCon.firstQuery(sql);
                if (!jsonObject.isEmpty()){
                    Boolean needShopNext = jsonObject.getBoolean("needShopNext");
                    if (needShopNext){
                        return needShopNext;
                    }
                }
                CommonUtils.sleep(2000);

            }
        }finally {
            logisticsCon.close();
        }

        return false;


    }


}
