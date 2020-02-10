package com.yolyn.authpay.service;

import com.yolyn.authpay.common.CommonException;
import com.yolyn.authpay.enums.CommonRespEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Random;

/**
 * @author houyl
 * @date 2020/2/6 17:23
 * @description
 */
@Slf4j
@Component
public class VerifyCodeService {
    private Logger logger = LoggerFactory.getLogger(VerifyCodeService.class);
    private Properties properties;
    private Jedis jedis;

    public VerifyCodeService() {
        try {
            properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource("common.properties"));
            jedis = new Jedis(properties.getProperty("common.redis.host"));
        } catch (Exception e) {
            StringWriter stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            logger.error(stringWriter.toString());
            throw new CommonException(CommonRespEnum.SYSTEM_ERROR);
        }

    }

    /**
     * 生成验证码
     *
     * @param user
     * @return
     */
    public String createVerifyCode(String user) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        SetParams setParams = new SetParams();
        setParams.ex(60);
        jedis.set(user, stringBuilder.toString(), setParams);
        logger.info("verifyCode is " + stringBuilder.toString());
        return stringBuilder.toString();
    }

    /**
     * 校验验证码
     *
     * @param user
     * @param verifyCode
     * @return
     */
    public boolean checkVerifyCode(String user, String verifyCode) {
        String code = jedis.get(user);
        if (code != null && verifyCode.equals(code)) {
            return true;
        } else {
            return false;
        }


    }

    public static void main(String[] args) {
//        System.out.println(createVerifyCode());
        VerifyCodeService verifyCodeService = new VerifyCodeService();
//        verifyCodeService.createVerifyCode("abc");
//        verifyCodeService.createVerifyCode("abc");
//        System.out.println(verifyCodeService.checkVerifyCode("abc","581773"));
    }
}
