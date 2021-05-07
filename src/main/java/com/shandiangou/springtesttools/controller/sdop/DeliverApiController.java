package com.shandiangou.springtesttools.controller.sdop;

import com.shandiangou.springtesttools.dao.DadaDeliverMockDao;
import com.shandiangou.springtesttools.dao.MtDeliverMockDao;
import com.shandiangou.springtesttools.dao.ResultDao;
import com.shandiangou.springtesttools.services.deliver.MockDeliverStatusService;
import com.tools.api.common.httpclient.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * @ClassName: DeliverApiController
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-03 16:25
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/deliver")
public class DeliverApiController {

    @Autowired
    private MockDeliverStatusService mockService;

    @PostMapping("/mt/update")
    public ResultDao mockMeituanDeliverStatus(@RequestBody  @Valid MtDeliverMockDao mockDao,
                                              BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResultDao(bindingResult.getFieldError().getDefaultMessage());
        }
        ResponseUtils responseUtils = mockService.mockMtDeliverStatus(mockDao);
        return new ResultDao(responseUtils.bodyStr());
    }

    @PostMapping("/dada/update")
    public ResultDao mockDadaDeliverStatus(@RequestBody  @Valid DadaDeliverMockDao mockDao,
                                              BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            return new ResultDao(bindingResult.getFieldError().getDefaultMessage());
        }
        System.out.println("hello word");
        ResponseUtils responseUtils = mockService.mockDadaDeliverStatus(mockDao);
        return new ResultDao(responseUtils.bodyStr());
    }


}
