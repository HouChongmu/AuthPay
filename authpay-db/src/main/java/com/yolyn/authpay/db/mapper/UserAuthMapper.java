package com.yolyn.authpay.db.mapper;

import com.yolyn.authpay.db.pojo.UserAuth;
import org.apache.ibatis.annotations.Param;

/**
 * @author houyl
 * @date 2020/1/30 16:09
 * @description
 */
public interface UserAuthMapper {
    UserAuth selectUserAuth(@Param(value = "idNo") String idNo, @Param(value = "cardNo") String cardNo);
    Integer insertUserAuth(UserAuth userAuth);
    Integer updateUserAuth(UserAuth userAuth);
}
