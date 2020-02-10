package com.yolyn.authpay.common;

import com.yolyn.authpay.enums.CommonRespEnum;

/**
 * @author houyl
 * @date 2020/1/31 13:36
 * @description
 */
public class CommonException extends RuntimeException {
    private String msg;
    private String code;
    private Object obj;

    public CommonException() {
        super();
    }

    public CommonException(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public CommonException(CommonRespEnum respEnum) {
        this.msg = respEnum.getMsg();
        this.code = respEnum.getCode();
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "CommonException:["+code+":"+msg+"]";
    }
}
