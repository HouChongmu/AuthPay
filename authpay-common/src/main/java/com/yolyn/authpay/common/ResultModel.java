package com.yolyn.authpay.common;

import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author dy
 * @since 2015-12-01  & JDK 1.8.0_91
 */
@Getter
@ToString
public class ResultModel<T> implements Serializable {

    public static final String SUCCESS    = "000000";
    public static final String ERROR      = "FE9999";

    /**
     * 返回数据总数量
     */
    private String             totalCount = "0";

    /**
     * 接口返回对象
     */
    private T                  result;

    /**
     * 接口返回code
     */
    private String             code;
    /**
     * 接口返回msg
     */
    private String             msg;

    public ResultModel() {
    }

    public ResultModel(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultModel(T result, String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }

    public ResultModel setFailed(String msg) {
        this.msg = msg;
        this.code = ERROR;
        return this;
    }

    public ResultModel setSuccess(T result) {
        this.result = result;
        this.msg = "SUCCESS";
        this.code = SUCCESS;
        return this;
    }

    public ResultModel setSuccess(T result, String totalCount) {
        this.result = result;
        this.totalCount = totalCount;
        this.msg = "SUCCESS";
        this.code = SUCCESS;
        return this;
    }

    public ResultModel setSuccess(String msg) {
        this.msg = msg;
        this.code = SUCCESS;
        return this;
    }

    public boolean isSuccess() {
        if (SUCCESS.equals(code)) {
            return true;
        } else {
            return false;
        }
    }
}
