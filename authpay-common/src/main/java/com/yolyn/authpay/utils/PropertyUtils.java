package com.yolyn.authpay.utils;

import com.yolyn.authpay.common.CommonException;
import com.yolyn.authpay.enums.CommonRespEnum;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Properties;

/**
 * @author houyl
 * @date 2020/2/8 11:28
 * @description
 */
public class PropertyUtils {
    public static Object getClassPathProperties(String path, String key) {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(path));
            return properties.get(key);
        } catch (Exception e) {
            throw new CommonException(CommonRespEnum.SYSTEM_ERROR);
        }
    }
}
