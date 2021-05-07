package com.shandiangou.springtesttools.dao;

import lombok.Data;

/**
 * @ClassName: LoginDao
 * @CreateUser: wangxiaohao
 * @CreateDate: 2021-03-19 16:08
 * @Description:
 */
@Data
public class LoginDao {


    private String getuiToken;
    private String password;
    private String mipushToken;
    private String kaId;
    private String role;
    private String code;
    private String landmarkId;
    private String mobile;
    private String iosToken;
    private String umengToken;

}
