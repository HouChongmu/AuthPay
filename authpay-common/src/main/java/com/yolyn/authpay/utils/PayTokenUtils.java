package com.yolyn.authpay.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * @author houyl
 * @date 2020/2/5 13:32
 * @description
 */
public class PayTokenUtils {
    public static String createToken(String ... seeds){
        StringBuilder stringBuilder=new StringBuilder();
        if (seeds.length>0){
            Arrays.sort(seeds);
            for (String s:seeds){
                stringBuilder.append(s);
            }
            return MD5Utils.md5("create payToken",stringBuilder.toString().getBytes());
        } else {
            stringBuilder.append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

            return UUID.nameUUIDFromBytes(stringBuilder.toString().getBytes()).toString();
        }
    }
}
