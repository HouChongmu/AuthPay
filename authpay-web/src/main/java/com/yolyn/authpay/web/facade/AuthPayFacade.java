package com.yolyn.authpay.web.facade;

import com.yolyn.authpay.api.IAuthPayService;
import com.yolyn.authpay.api.request.PayApplyReq;
import com.yolyn.authpay.api.request.PayConfirmReq;
import com.yolyn.authpay.api.response.PayApplyResp;
import com.yolyn.authpay.api.response.PayConfirmResp;
import com.yolyn.authpay.common.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author houyl
 * @date 2020/2/11 15:58
 * @description
 */
@Slf4j
@Component
public class AuthPayFacade {
    @Autowired
    private IAuthPayService  authPayService;

    public ResultModel<PayApplyResp> handleApply(PayApplyReq payApplyReq){
        return authPayService.payRequest(payApplyReq);
    }
    public ResultModel<PayConfirmResp> handleConfirm(PayConfirmReq payConfirmReq){
        return authPayService.payConfirm(payConfirmReq);
    }
}
