package com.lc.lcserve.controller.dto;

import lombok.Data;

@Data
public class LoginDTO {

    /**
     * 用户名
     */
    private String uname;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;
}
