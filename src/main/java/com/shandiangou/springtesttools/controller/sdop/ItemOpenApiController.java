package com.shandiangou.springtesttools.controller.sdop;

import com.shandiangou.springtesttools.common.EnvCommon;
import com.shandiangou.springtesttools.dao.ItemOpenApiDao;
import com.shandiangou.springtesttools.services.sdop.ItemOpenApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @ClassName: ItemOpenApiController
 * @CreateUser: wangxiaohao
 * @CreateDate: 2020-12-31 15:32
 * @Description:
 */

@RestController
@RequestMapping("/sdop")
public class ItemOpenApiController {

    @Autowired
    private ItemOpenApiService itemOpenApiService;


    @PostMapping("/item/open/req")
    public String ItemOpenApiReq(@RequestBody ItemOpenApiDao itemOpenApiDao){

        EnvCommon.setEnv(itemOpenApiDao.getEnv());
        return itemOpenApiService.itemOpenApiReq(itemOpenApiDao).bodyStr();


    }


}
