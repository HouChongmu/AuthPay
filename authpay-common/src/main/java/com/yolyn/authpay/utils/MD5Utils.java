package com.yolyn.authpay.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * @author houyl
 * @date 2020/2/5 18:00
 * @description
 */
public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    public static String md5(String logStr, byte[] input) {
        if (input != null && input.length != 0) {
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                messageDigest.reset();
                messageDigest.update(input);
                byte[] byteArray = messageDigest.digest();
                StringBuilder md5StrBuff = new StringBuilder();

                for (int i = 0; i < byteArray.length; ++i) {
                    String hex = Integer.toHexString(255 & byteArray[i]);
                    if (hex.length() == 1) {
                        md5StrBuff.append("0").append(hex);
                    } else {
                        md5StrBuff.append(hex);
                    }
                }

                return md5StrBuff.toString();
            } catch (Exception var7) {
                logger.warn("{}md5 encrypt failure:", logStr, var7);
                return "";
            }
        } else {
            return "";
        }
    }
}
