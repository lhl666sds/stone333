package com.lc.lcserve.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String uname;
    private String password;
}
