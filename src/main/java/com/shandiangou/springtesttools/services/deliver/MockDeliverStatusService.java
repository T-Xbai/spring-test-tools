package com.shandiangou.springtesttools.services.deliver;

import com.shandiangou.springtesttools.dao.DadaDeliverMockDao;
import com.shandiangou.springtesttools.dao.MtDeliverMockDao;
import com.tools.api.common.httpclient.ResponseUtils;

/**
 * @ClassName: MtDeliverMockService
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 17:10
 * @Description:
 */

public interface MockDeliverStatusService {

    /**  模拟美团配送状态 */
    ResponseUtils mockMtDeliverStatus(MtDeliverMockDao mockDao);

    /**  模拟达达配送状态 */
    ResponseUtils mockDadaDeliverStatus(DadaDeliverMockDao mockDao);

}
