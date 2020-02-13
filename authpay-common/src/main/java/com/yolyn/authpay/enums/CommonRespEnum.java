package com.yolyn.authpay.enums;

/**
 * @author houyl
 * @date 2020/1/31 14:46
 * @description
 */
public enum CommonRespEnum {
    COMMON_SUCCESS("000000", "处理成功"),
    COMMON_FAIL("FE9999", "处理失败"),
    COMMON_MISS_PARAMS("000001", "参数缺失"),
    //用户相关
    USER_NOT_EXIST("010001", "用户不存在"),
    USER_AUTH_FAILED("010002", "认证失败"),
    //银行卡相关
    CARD_NOT_EXIST("020001", "银行卡不存在"),
    CARD_LACK_BALANCE("020002", "余额不足"),
    //系统错误
    SYSTEM_ERROR("FE9999", "系统错误"),
    //验证码相关
    VERIFYCODE_ERROR("030001","验证码错误"),
    ;
    private final String code;
    private final String msg;

    CommonRespEnum(String code, String msg) {
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
