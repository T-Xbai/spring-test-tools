package com.shandiangou.springtesttools.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName: DadaDeliverMockDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-05 14:22
 * @Description:
 */
@Data
public class DadaDeliverMockDao {


    private String appKey;

    private String appSecret;

    private String sourceId;

    @NotBlank(message ="ORDER_ID  不能为空哦")
    private String orderId;

    @NotEmpty(message = "DELIVER_TYPE 不能为空哦~")
    private String deliverStatus;

    private String reason;

    private String notifyUrl;
}
