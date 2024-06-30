package com.lc.lcserve.controller.request;

import lombok.Data;

@Data
public class CreateOrderRequest {

    private String nickName;

    private String phone;

    private String shipAddress;

    private String others;
}
