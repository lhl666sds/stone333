package com.lc.lcserve.exception;

public enum  AppExceptionCodeMsg {
    TOKEN_FAILED(40000,"token验证失败，请重新登录!"),
    ID_NOT_EXISTS(10000,"ID不存在"),
    USERNAME_IS_EXISTS(10001,"用户名已经被使用"),
    USERNAME_PASSWORD_INCORRECT(10002,"用户名或密码错误"),
    USER_NOT_EXIST(10003,"用户不存在"),
    OLD_PASSWORD_INCORRECT(10004,"原密码错误"),
    HOUSE_NOT_EXIST(10005,"房源不存在"),
    HOUSE_IS_RENT(10006,"房屋在租，不可操作！"),
    POST_NOT_EXIST(10007,"帖子不存在"),
    WANTED_TIME_OUT(10008,"求租的租房到期时间已过"),
    VALIDATION_FAILED(50000,"参数校验失败");

    private int code ;
    private String msg ;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    AppExceptionCodeMsg(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
}
