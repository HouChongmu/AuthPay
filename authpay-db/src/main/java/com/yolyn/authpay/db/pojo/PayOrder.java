package com.yolyn.authpay.db.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/2/8 20:52
 * @description
 */
@Data
@ToString
@NoArgsConstructor
public class PayOrder {
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商户订单号
     */
    private String merchantOrderNo;
    /**
     * 用户姓名
     */
    private String userName;
    private String userIdNo;
    private String userAcctNo;
    private String phoneNo;
    private String amount;
}
