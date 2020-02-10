package com.yolyn.authpay.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author houyl
 * @date 2020/1/26 14:18
 * @description 付款请求
 */
@Data
@NoArgsConstructor
@ToString
public class PayApplyReq {
    /**
     * 用户姓名
     */
    @NotBlank(message = "userName不能为空")
    private String userName;
    /**
     * 用户身份证号
     */
    @NotBlank(message = "userIdNo不能为空")
    private String userIdNo;
    /**
     * 用户账号
     */
    @NotBlank(message = "userAcctNo不能为空")
    private String userAcctNo;
    /**
     * 预留手机号
     */
    @NotBlank(message = "phoneNo不能为空")
    private String phoneNo;
    /**
     * 交易金额
     */
    @NotBlank(message = "amount不能为空")
    private String amount;
    /**
     * 对手账号
     */
    @NotBlank(message = "opponentAcctNo不能为空")
    private String opponentAcctNo;
    /**
     * 商户唯一订单号
     */
    @NotBlank(message = "merchantOrderNo不能为空")
    private String merchantOrderNo;

}
