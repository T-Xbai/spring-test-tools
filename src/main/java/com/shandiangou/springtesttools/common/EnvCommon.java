package com.shandiangou.springtesttools.common;

import com.tools.api.common.dao.config.ConfigDao;
import com.tools.api.common.enums.EnvEnum;
import com.tools.api.common.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: EnvCommon
 * @CreateUser: wangxiaohao
 * @CreateDate: 2020-12-31 15:49
 * @Description:
 */
public class EnvCommon {

    private static final Logger log = LoggerFactory.getLogger(EnvCommon.class);

    public static String setEnv(String env) {

        if (StringUtils.isEmpty(env)) {
            log.error("环境变量参数为空,默认走 GRAY 环境 ~");
        }
        switch (env) {
            case "ONLINE":
                return "http://api.51xianqu.com";
            case "DAILY":
                return "http://apicallback.51xianqu.com";
            default:
                return "http://gray.api.51xianqu.com";
        }
    }
}
