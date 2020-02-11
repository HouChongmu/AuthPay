package com.yolyn.authpay.web;

import com.yolyn.authpay.core.aspect.AspectHandler;
import com.yolyn.authpay.core.service.AuthPayServiceImpl;
import com.yolyn.authpay.db.dao.UserDao;
import com.yolyn.authpay.service.VerifyCodeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author houyl
 * @date 2020/2/11 17:56
 * @description
 */
@ComponentScan(basePackageClasses = {Application.class, AspectHandler.class, AuthPayServiceImpl.class, UserDao.class, VerifyCodeService.class})
@EnableAutoConfiguration
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class })
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
