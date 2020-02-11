package com.yolyn.authpay.core.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yolyn.authpay.api.IAuthPayService;
import com.yolyn.authpay.api.request.PayApplyReq;
import com.yolyn.authpay.api.response.PayApplyResp;
import com.yolyn.authpay.common.ResultModel;
import com.yolyn.authpay.core.config.CoreConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author houyl
 * @date 2020/2/2 21:18
 * @description
 */
//测试开始时自动创建spring上下文
@RunWith(SpringJUnit4ClassRunner.class)
//加载配置
@ContextConfiguration(classes = CoreConfig.class)
public class AuthPayServiceImplTest {
    @Autowired
    private IAuthPayService authPayService;

    @Test
    public void authPayRequestTest() {
        PayApplyReq request = new PayApplyReq();
        request.setAmount("1324");
        request.setUserName("侯允林");
        request.setPhoneNo("18256158248");
        request.setUserIdNo("34082519960408105X");
        request.setUserAcctNo("123456");
        request.setOpponentAcctNo("1234565");
        request.setMerchantOrderNo("123456789");
//        ResultModel<PayApplyResp> rm= authPayService.payRequest(request);
        System.out.println(JSONObject.toJSONString(request));
    }
}
