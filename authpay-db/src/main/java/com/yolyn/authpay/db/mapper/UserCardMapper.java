package com.yolyn.authpay.db.mapper;

import com.yolyn.authpay.db.pojo.UserCard;

/**
 * @author houyl
 * @date 2020/1/29 13:52
 * @description
 */
public interface UserCardMapper {
    UserCard selectByCardNo(String cardNo);
    Integer updateUserCardBalance(UserCard userCard);
}
