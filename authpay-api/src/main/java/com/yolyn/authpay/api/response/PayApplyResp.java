package com.yolyn.authpay.api.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/1/27 23:02
 * @description 付款申请响应
 */
@Data
@ToString
@NoArgsConstructor
public class PayApplyResp {
    /**
     * 支付令牌
     */
    private String payToken;
    /**
     * 商户订单号
     */
    private String merchantTransNo;
}
