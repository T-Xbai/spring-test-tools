package com.shandiangou.springtesttools.dao;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


/**
 * @ClassName: MtDeliverMockDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 16:43
 * @Description: 美团配送状态 Mock  实体类
 */

@Data
public class MtDeliverMockDao {


    @NotEmpty(message ="APP_KEY 必传哦~")
    private String appKey;

    @NotBlank(message ="APP_SECRET 必传哦~")
    private String appSecret;

    @NotBlank(message ="ORDER_ID 必传哦~")
    private String orderId;

    @NotBlank(message ="DELIVER_STATUS 必传哦~")
    private String deliverStatus;

}






