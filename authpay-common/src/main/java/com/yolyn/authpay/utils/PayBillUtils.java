package com.yolyn.authpay.utils;

import java.util.Random;

/**
 * @author houyl
 * @date 2020/2/9 12:36
 * @description
 */
public class PayBillUtils {
    public static String createBill(String... seeds) {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
