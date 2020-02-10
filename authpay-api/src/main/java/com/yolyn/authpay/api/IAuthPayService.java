package com.yolyn.authpay.api;

import com.yolyn.authpay.api.request.PayConfirmReq;
import com.yolyn.authpay.api.request.PayApplyReq;
import com.yolyn.authpay.api.response.PayConfirmResp;
import com.yolyn.authpay.api.response.PayApplyResp;
import com.yolyn.authpay.common.ResultModel;

/**
 * @author houyl
 * @date 2020/1/27 22:56
 * @description
 */
 public interface IAuthPayService {
 /**
  * 支付申请
  * @param request
  * @return
  */
  ResultModel<PayApplyResp> payRequest(PayApplyReq request);

 /**
  * 支付验证
  * @param confirm
  * @return
  */
  ResultModel<PayConfirmResp> payConfirm(PayConfirmReq confirm);
}
