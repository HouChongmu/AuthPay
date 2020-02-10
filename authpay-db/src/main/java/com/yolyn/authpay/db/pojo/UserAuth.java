package com.yolyn.authpay.db.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/1/29 13:50
 * @description
 */
@Data
@ToString
@NoArgsConstructor
public class UserAuth {
    private String userName;
    private String cardNo;
    private String authStatus;
    private String phoneNo;
    private String idNo;
}
