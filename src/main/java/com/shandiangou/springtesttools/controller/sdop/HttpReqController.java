package com.shandiangou.springtesttools.controller.sdop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shandiangou.springtesttools.dao.HttpReqMockDao;
import com.shandiangou.springtesttools.dao.ResultDao;
import com.shandiangou.springtesttools.services.HttpReqMockService;
import com.tools.api.common.httpclient.HttpReqUtils;
import com.tools.api.common.httpclient.ResponseUtils;
import lombok.var;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * @ClassName: HttpReqController
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-02-07 17:16
 * @Description:
 */
@RestController
@RequestMapping("/postman")
public class HttpReqController {

    @Autowired
    private HttpReqMockService mockService;


    @PostMapping("/doReq")
    public ResultDao doReq(@RequestBody HttpReqMockDao mockDao) {
        ResponseUtils res = mockService.doReq(mockDao);
        return new ResultDao(res.bodyStr());
    }

    @PostMapping("/doReq/upload")
    public ResultDao doReq(HttpServletRequest request) throws IOException {

        if (request instanceof MultipartHttpServletRequest) {

            var url = "";
            var method = "";
            var headers = new JSONObject();

            Map<String, String[]> parameterMap = request.getParameterMap();


            try {
                url = parameterMap.get("url")[0];
                method = parameterMap.get("method")[0];
                headers = JSON.parseObject(parameterMap.get("headers")[0]);

            } catch (Exception e) {
                return new ResultDao(-1, "请检查请求参数哦~ ， URL、METHOD、HEADERS、FORMDATA 必传哦~");
            }


            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

            MultipartFile multipartFile = multipartRequest.getFile("file");
            InputStream inputStream = Objects.requireNonNull(multipartFile).getInputStream();


            HttpReqUtils httpReqUtils = new HttpReqUtils()
                    .url(url)
                    .addBinaryBody("file", inputStream, ContentType.MULTIPART_FORM_DATA, multipartFile.getOriginalFilename());


            for (String key : parameterMap.keySet()) {
                if (!(key.equals("url") || key.equals("method") || key.equals("headers") || key.equals("formdata"))) {
                    httpReqUtils.addTextBody(key, String.valueOf(parameterMap.get(key)[0]));
                }
            }


            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpReqUtils.putHeader(key, String.valueOf(headers.get(key)));
                }
            }

            ResponseUtils res = httpReqUtils.upload();

            return new ResultDao(res.bodyStr());
        }
        return new ResultDao(-1, "failure");
    }


}
