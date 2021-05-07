package com.shandiangou.springtesttools.services.impl.deliver;

import com.alibaba.fastjson.JSONObject;
import com.shandiangou.springtesttools.dao.DadaDeliverMockDao;
import com.shandiangou.springtesttools.dao.MtDeliverMockDao;
import com.shandiangou.springtesttools.db.logistics.CourierOrderDB;
import com.shandiangou.springtesttools.enums.DadaDeliverStatusEnum;
import com.shandiangou.springtesttools.page.deliver.DadaDeliverPathPage;
import com.shandiangou.springtesttools.page.deliver.MtDeliverPathPage;
import com.shandiangou.springtesttools.services.deliver.MockDeliverStatusService;
import com.tools.api.common.httpclient.ResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static com.shandiangou.springtesttools.enums.MtDeliverStatusEnum.*;

/**
 * @ClassName: MtDeliverMockServiceImpl
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 17:17
 * @Description:
 */
@Service
public class MockDeliverStatusServiceImpl implements MockDeliverStatusService {

    @Override
    public ResponseUtils mockMtDeliverStatus(MtDeliverMockDao mockDao) {

        MtDeliverPathPage mtDeliverPathPage = new MtDeliverPathPage(mockDao.getAppKey(), mockDao.getAppSecret());

        ResponseUtils responseUtils;


        JSONObject mtDeliverIdJson = CourierOrderDB.getMtDeliverId(mockDao.getOrderId());
        Long deliverId = mtDeliverIdJson.getLong("id");
        String mtPeisongId = mtDeliverIdJson.getString("thirdReturnOrderId");

        if (mockDao.getDeliverStatus().equals(ACCEPT.getDesc())) {

            responseUtils = mtDeliverPathPage.mockAcceptOrder(deliverId, mtPeisongId, mockDao);
        } else if (mockDao.getDeliverStatus().equals(PICK_UP.getDesc())) {
            responseUtils = mtDeliverPathPage.mockPickUpOrder(deliverId, mtPeisongId, mockDao);
        } else if (mockDao.getDeliverStatus().equals(DELIVER.getDesc())) {
            responseUtils = mtDeliverPathPage.mockDeliverOrder(deliverId, mtPeisongId, mockDao);
        } else if (mockDao.getDeliverStatus().equals(REARRANGE.getDesc())) {
            responseUtils = mtDeliverPathPage.mockRearrangeOrder(deliverId, mtPeisongId, mockDao);
        } else if (mockDao.getDeliverStatus().equals(CANCEL.getDesc())) {
            responseUtils = mtDeliverPathPage.mockCancelOrder(deliverId, mtPeisongId);
        } else {
            responseUtils = mtDeliverPathPage.mockAcceptOrder(deliverId, mtPeisongId, mockDao);
        }

        return responseUtils;
    }

    @Override
    public ResponseUtils mockDadaDeliverStatus(DadaDeliverMockDao mockDao) {

        DadaDeliverPathPage dadaDeliverPathPage;

        if ((StringUtils.isEmpty(mockDao.getAppKey()) || mockDao.getAppKey().equals("")) ||
                (StringUtils.isEmpty(mockDao.getAppSecret()) || mockDao.getAppSecret().equals("")) ||
                (StringUtils.isEmpty(mockDao.getSourceId()) || mockDao.getSourceId().equals(""))) {
            dadaDeliverPathPage = new DadaDeliverPathPage();
        } else {
            dadaDeliverPathPage = new DadaDeliverPathPage(mockDao.getAppKey(), mockDao.getAppSecret(), mockDao.getSourceId());
        }

        ResponseUtils responseUtils;
        if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.ACCEPT.getDesc())) {
            responseUtils = dadaDeliverPathPage.orderAccept(mockDao.getOrderId());
        } else if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.CANCEL.getDesc())){
            responseUtils = dadaDeliverPathPage.orderCancel(mockDao.getOrderId(),mockDao.getReason());
        }else if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.EXPIRE.getDesc())){
            responseUtils = dadaDeliverPathPage.orderExpire(mockDao.getOrderId());
        }else if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.FETCH.getDesc())){
            responseUtils = dadaDeliverPathPage.orderFetch(mockDao.getOrderId());
        }else if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.FINISH.getDesc())){
            responseUtils = dadaDeliverPathPage.orderFinish(mockDao.getOrderId());
        }else if (mockDao.getDeliverStatus().equals(DadaDeliverStatusEnum.TRANSPORTER_CANCEL.getDesc())){
            responseUtils = dadaDeliverPathPage.transporterCancelOrder(mockDao.getOrderId(),mockDao.getNotifyUrl());
        }else {
            responseUtils = dadaDeliverPathPage.orderAccept(mockDao.getOrderId());
        }
        return responseUtils;
    }


}
