package com.yolyn.authpay.core.service;

import com.alibaba.fastjson.JSONObject;
import com.yolyn.authpay.api.IAuthPayService;
import com.yolyn.authpay.api.request.PayApplyReq;
import com.yolyn.authpay.api.request.PayConfirmReq;
import com.yolyn.authpay.api.response.PayApplyResp;
import com.yolyn.authpay.api.response.PayConfirmResp;
import com.yolyn.authpay.common.CommonException;
import com.yolyn.authpay.common.ResultModel;
import com.yolyn.authpay.core.aspect.CheckMethod;
import com.yolyn.authpay.db.dao.UserAuthDao;
import com.yolyn.authpay.db.dao.UserCardDao;
import com.yolyn.authpay.db.dao.UserDao;
import com.yolyn.authpay.db.pojo.PayOrder;
import com.yolyn.authpay.db.pojo.User;
import com.yolyn.authpay.db.pojo.UserAuth;
import com.yolyn.authpay.db.pojo.UserCard;
import com.yolyn.authpay.enums.AuthStatusEnums;
import com.yolyn.authpay.enums.CommonRespEnum;
import com.yolyn.authpay.service.VerifyCodeService;
import com.yolyn.authpay.utils.PayBillUtils;
import com.yolyn.authpay.utils.PayTokenUtils;
import com.yolyn.authpay.utils.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Properties;

/**
 * @author houyl
 * @date 2020/1/29 21:38
 * @description
 */
@Slf4j
@Component
public class AuthPayServiceImpl implements IAuthPayService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserCardDao userCardDao;
    @Autowired
    private UserAuthDao userAuthDao;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @CheckMethod(name = "支付申请")
    public ResultModel<PayApplyResp> payRequest(PayApplyReq request) {
        //验签
//        checkSign();
        //校验用户是否合法
        checkUser(request);
        //查询是否有认证记录
        if (!checkAuthRecords(request)) {
            //认证
            checkAuth(request);
        }
        //余额校验
        checkBalance(request);
        return payApply(request);
    }

    @CheckMethod(name = "支付验证")
    public ResultModel<PayConfirmResp> payConfirm(PayConfirmReq confirm) {
        //验签
        //checkSign();
        try {
            //验证验证码
            Jedis jedis = new Jedis((String) PropertyUtils.getClassPathProperties("core.properties", "core.redis.host"));
            String tokenContent = jedis.get(confirm.getPayToken());
            PayApplyReq payApplyReq = JSONObject.parseObject(tokenContent, PayApplyReq.class);
            boolean isCorrected = verifyCodeService.checkVerifyCode(payApplyReq.getUserIdNo(), confirm.getVerifyCode());
            if (!isCorrected) {
                throw new CommonException(CommonRespEnum.SYSTEM_ERROR);
            }
            //创单
            PayOrder payOrder = createPayOrder(payApplyReq);
            //卡记录
            UserCard userCard = userCardDao.selectByCardNo(payApplyReq.getUserAcctNo());
            if (null == userCard) {
                throw new CommonException(CommonRespEnum.CARD_NOT_EXIST);
            }
            checkBalance(payApplyReq);
            //扣款
            Integer balance = Integer.parseInt(userCard.getAmount()) - Integer.parseInt(payApplyReq.getAmount());
            userCard.setAmount(String.valueOf(balance));
            //更新卡
            userCardDao.updateUserCardBalance(userCard);
            //创建返回
            PayConfirmResp payConfirmResp = new PayConfirmResp();
            payConfirmResp.setAmount(payApplyReq.getAmount());
            payConfirmResp.setOrderNo(payOrder.getOrderNo());
            payConfirmResp.setOrderStatus("1");
            ResultModel<PayConfirmResp> rm = new ResultModel<PayConfirmResp>();
            rm.setSuccess(payConfirmResp);
            return rm;
        } catch (Exception e) {
            throw new CommonException(CommonRespEnum.COMMON_FAIL);
        }
    }

    /**
     * 构建payOrder
     *
     * @param payApplyReq
     * @return
     */
    private PayOrder createPayOrder(PayApplyReq payApplyReq) {
        PayOrder payOrder = new PayOrder();
        payOrder.setUserAcctNo(payApplyReq.getUserAcctNo());
        payOrder.setMerchantOrderNo(payApplyReq.getMerchantOrderNo());
        payOrder.setUserIdNo(payApplyReq.getUserIdNo());
        payOrder.setPhoneNo(payApplyReq.getPhoneNo());
        payOrder.setUserName(payApplyReq.getUserName());
        payOrder.setAmount(payApplyReq.getAmount());
        String orderNo = PayBillUtils.createBill();
        payOrder.setOrderNo(orderNo);
        return payOrder;
    }


    /**
     * 用户校验
     *
     * @param request
     */
    private void checkUser(PayApplyReq request) {
        User user = userDao.selectByUserName(request.getUserName());
        if (StringUtils.isEmpty(user)) {
            throw new CommonException(CommonRespEnum.USER_NOT_EXIST);
        }
    }

    /**
     * 认证
     *
     * @param request
     */
    private void checkAuth(PayApplyReq request) {
        UserCard userCard = userCardDao.selectByCardNo(request.getUserAcctNo());
        if (null != userCard && request.getUserName().equals(userCard.getUserName())
                && request.getUserIdNo().equals(userCard.getIdNo())
                && request.getPhoneNo().equals(userCard.getPhoneNo())) {
            UserAuth userAuth = userAuthDao.selectByCardNo(request.getUserIdNo(), request.getUserAcctNo());
            if (null == userAuth) {
                userAuth = new UserAuth();
                userAuth.setUserName(userCard.getUserName());
                userAuth.setCardNo(userCard.getCardNo());
                userAuth.setIdNo(userCard.getIdNo());
                userAuth.setPhoneNo(userCard.getPhoneNo());
                userAuth.setAuthStatus(AuthStatusEnums.AUTHED.getCode());
                userAuthDao.insertUserAuth(userAuth);
            } else {
                userAuth.setAuthStatus(AuthStatusEnums.AUTHED.getCode());
                userAuthDao.updateUserAuth(userAuth);
            }
        } else {
            throw new CommonException(CommonRespEnum.USER_AUTH_FAILED);
        }
    }

    /**
     * 校验认证记录
     *
     * @param request
     * @return
     */
    private boolean checkAuthRecords(PayApplyReq request) {
        UserAuth userAuth = userAuthDao.selectByCardNo(request.getUserIdNo(), request.getUserAcctNo());
        if (AuthStatusEnums.AUTHED.getCode().equals(userAuth.getAuthStatus())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验余额
     *
     * @param request
     */
    private void checkBalance(PayApplyReq request) {
        UserCard userCard =userCardDao.selectByCardNo(request.getUserAcctNo());
        if (Integer.parseInt(userCard.getAmount())<Integer.parseInt(request.getAmount())){
            throw new CommonException(CommonRespEnum.CARD_LACK_BALANCE);
        }

    }

    /**
     * 支付申请
     *
     * @param request
     * @return
     */
    private ResultModel<PayApplyResp> payApply(PayApplyReq request) {
        try {
            ResultModel<PayApplyResp> payRequestRespRm = new ResultModel<PayApplyResp>();
            String token = PayTokenUtils.createToken(new String[]{request.getUserIdNo(), request.getUserAcctNo()});
            //缓存
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("core.redis.verifycode.expiretime"));
            Jedis jedis = new Jedis(properties.getProperty("core.redis.host"));
            SetParams setParams = new SetParams();
            setParams.ex(60);
            String msg = JSONObject.toJSONString(request);
            jedis.set(token, msg, setParams);
            PayApplyResp payRequestResp = new PayApplyResp();
            payRequestResp.setPayToken(token);
            payRequestResp.setMerchantTransNo(request.getMerchantOrderNo());
            payRequestRespRm.setSuccess(payRequestResp);
            verifyCodeService.createVerifyCode(request.getUserIdNo());
            return payRequestRespRm;
        } catch (Exception e) {
            throw new CommonException(CommonRespEnum.SYSTEM_ERROR);
        }
    }


}
