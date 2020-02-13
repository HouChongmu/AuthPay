package com.yolyn.authpay.core.aspect;

import com.google.common.base.Throwables;
import com.yolyn.authpay.common.CommonException;
import com.yolyn.authpay.common.ResultModel;
import com.yolyn.authpay.enums.CommonRespEnum;
import com.yolyn.authpay.utils.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * @author houyl
 * @date 2020/1/30 21:40
 * @description
 */
@Slf4j
@Aspect
@Component
public class AspectHandler {
    Logger logger = Logger.getLogger(AspectHandler.class);

    //指定包下的任意类，任意方法
    @Before("execution(* com.yolyn.authpay.core.service..*.*(..))")
    private void invokeLog() {
    }

    @Pointcut("@annotation(params)")
    private void pointCutMethod(CheckMethod params) {
    }

    @Around("pointCutMethod(params)")
    private ResultModel checkParams(ProceedingJoinPoint pjp, CheckMethod params) {
        ResultModel resultModel = null;
        try {


            Object[] args = pjp.getArgs();
            if (null != args && args.length > 0) {
                logger.info(String.format("{%s},请求参数:{%s}", params.name(), args[0].toString()));
                //参数校验
                ValidateUtils.validateBean(args[0]);
                resultModel = (ResultModel) pjp.proceed();
            }
        } catch (CommonException e) {
            //自定义异常
            resultModel = new ResultModel(e.getCode(), e.getMsg());
            logger.error(Throwables.getStackTraceAsString(e));
        } catch (Throwable e) {
            //系统异常
            resultModel = new ResultModel();
            resultModel.setFailed(CommonRespEnum.SYSTEM_ERROR.getMsg());
            logger.error(Throwables.getStackTraceAsString(e));
        }
        return resultModel;
    }
}
