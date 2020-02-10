package com.yolyn.authpay.api.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author houyl
 * @date 2020/1/26 15:13
 * @description 付款验证
 */
@Data
@NoArgsConstructor
@ToString
public class PayConfirmReq {
    /**
     * 缓存令牌
     */
    @NotBlank(message = "payToken不能为空")
    private String payToken;
//    /**
//     * 商户侧用户号
//     */
//    private String userId;
    /**
     * 商户订单号
     */
    @NotBlank(message = "merchantTransNo不能为空")
    private String merchantTransNo;
    /**
     * 验证码
     */
    @NotBlank(message = "verifyCode不能为空")
    private String verifyCode;

}
