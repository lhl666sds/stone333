package com.lc.lcserve.model;

public enum InterfaceType {

    LOGIN(1,"用户登录"),
    INSERT(2,"插入"),
    SEARCH(3, "搜索"),
    CHANGE(4,"修改"),
    DELETE(5,"删除");

    // 接口类型
    private int code;
    // 接口类型描述
    private String message;

    InterfaceType(int code , String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }
}
