package com.yolyn.authpay.db.mapper;

import com.yolyn.authpay.db.pojo.User;

/**
 * @author houyl
 * @date 2020/1/30 13:33
 * @description
 */
public interface UserMapper {
    User selectByIdNo(String idNo);
}
