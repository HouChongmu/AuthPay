package com.yolyn.authpay.db.mapper;

import com.yolyn.authpay.db.pojo.UserAuth;

/**
 * @author houyl
 * @date 2020/1/30 16:09
 * @description
 */
public interface UserAuthMapper {
    UserAuth selectUserAuth(String idNo, String cardNo);
    Integer insertUserAuth(UserAuth userAuth);
    Integer updateUserAuth(UserAuth userAuth);
}
