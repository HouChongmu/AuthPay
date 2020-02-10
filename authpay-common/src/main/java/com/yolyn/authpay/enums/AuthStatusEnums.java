package com.yolyn.authpay.enums;

/**
 * @author houyl
 * @date 2020/2/4 16:19
 * @description
 */
public enum AuthStatusEnums {
    NOT_AUTH("0","未认证"),
    AUTHED("1","已认证"),
    AUTH_FAILED("2","认证失败");
    private final String code;
    private final String msg;

    AuthStatusEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
