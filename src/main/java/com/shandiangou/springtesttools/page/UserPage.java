package com.shandiangou.springtesttools.page;

import com.alibaba.fastjson.JSONObject;
import com.shandiangou.springtesttools.dao.LoginDao;
import com.tools.api.common.dao.config.ConfigDao;
import com.tools.api.common.httpclient.HttpReqUtils;
import com.tools.api.common.httpclient.ResponseUtils;

/**
 * @ClassName: UserPage
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-03-19 15:45
 * @Description:
 */
public class UserPage {

    public static String HOST = ConfigDao.SHANGOU_HOST();


    public static ResponseUtils appLogin(LoginDao loginDao){
        final String URL = "https://" + HOST.concat("/member/ka/login.do");
        return new HttpReqUtils(URL)
                .body(JSONObject.toJSONString(loginDao))
                .doPost();
    }



}
