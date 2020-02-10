package com.yolyn.authpay.db.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/1/29 13:51
 * @description
 */
@Data
@ToString
@NoArgsConstructor
public class UserCard {
    private String userName;
    private String phoneNo;
    private String cardNo;
    private String idNo;
    private String amount;
}
