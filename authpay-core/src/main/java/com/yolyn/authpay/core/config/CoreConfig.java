package com.yolyn.authpay.core.config;

import com.yolyn.authpay.core.aspect.AspectHandler;
import com.yolyn.authpay.core.service.AuthPayServiceImpl;
import com.yolyn.authpay.db.dao.UserDao;
import com.yolyn.authpay.service.VerifyCodeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author houyl
 * @date 2020/2/3 16:55
 * @description
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {AspectHandler.class, AuthPayServiceImpl.class, UserDao.class, VerifyCodeService.class})
public class CoreConfig {

}
