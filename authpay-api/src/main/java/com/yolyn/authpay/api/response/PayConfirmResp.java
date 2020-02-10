package com.yolyn.authpay.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/1/28 18:34
 * @description
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PayConfirmResp {
    /**
     * 交易订单号
     */
    private String orderNo;
    /**
     * 交易金额
     */
    private String amount;
    /**
     * 交易状态
     */
    private String orderStatus;
}
