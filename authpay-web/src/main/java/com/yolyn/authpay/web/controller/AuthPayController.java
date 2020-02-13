package com.yolyn.authpay.web.controller;

import com.yolyn.authpay.api.request.PayApplyReq;
import com.yolyn.authpay.api.request.PayConfirmReq;
import com.yolyn.authpay.common.ResultModel;
import com.yolyn.authpay.web.facade.AuthPayFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author houyl
 * @date 2020/2/11 13:57
 * @description
 */
@RestController
@RequestMapping("/yolyn/authpay")
public class AuthPayController {
    public Logger logger=LoggerFactory.getLogger(AuthPayController.class);
    @Autowired
    private AuthPayFacade authPayFacade;
    @PostMapping(value = "/apply")
    public ResultModel authPayReceiver(@RequestBody PayApplyReq payApplyReq){
        return authPayFacade.handleApply(payApplyReq);
    }
    @PostMapping(value = "/confirm")
    public ResultModel authPayConfirm(@RequestBody PayConfirmReq payConfirmReq){
        return authPayFacade.handleConfirm(payConfirmReq);
    }
}
