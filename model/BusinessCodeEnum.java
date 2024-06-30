//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.lc.lcserve.model;

import java.util.HashMap;
import java.util.Map;

public enum BusinessCodeEnum {
    DEFAULT_SUCCESS(200, "default success"),
    DEFAULT_SYS_ERROR(2000000999, "系统错误"),
    CHECK_PARAM_NO_RESULT(2000000100, "检测参数无结果"),
    CHECK_BIZ_NO_RESULT(2000000101, "检查业务无结果"),
    CHECK_ACTION_NO_RESULT(2000000102, "检查执行情况无结果"),
    CHECK_PARAM_NOT_MATCH(1000000001, "检查参数不匹配"),
    CHECK_BIZ_ERROR_VALIDATE_CODE(1000000002, "检查业务验证码错误"),
    CHECK_BIZ_ERROR_MOBILE_USED(1000000003, "检查业务电话号码已被使用"),
    FILTER_DISTINCET(100000004, "非法请求 ， 请重新刷新页面操作!!"),
    NO_LOGIN(1000000200, "未登录"),
    HTTP_NO_PERMISSION(1000000005, "没有权限!!"),
    HTTP_NO_LOGIN(100000006, "用户未登录!!!");

    private static final Map<Integer, BusinessCodeEnum> codeMap = new HashMap((int)((double)values().length / 0.75D) + 1);
    private int code;
    private String msg;

    public static BusinessCodeEnum valueOfCode(int code) {
        return (BusinessCodeEnum)codeMap.get(code);
    }

    private BusinessCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    static {
        BusinessCodeEnum[] var0 = values();
        int var1 = var0.length;

        for(int var2 = 0; var2 < var1; ++var2) {
            BusinessCodeEnum businessCodeEnum = var0[var2];
            codeMap.put(businessCodeEnum.getCode(), businessCodeEnum);
        }

    }
}
