package com.yolyn.authpay.db.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author houyl
 * @date 2020/1/29 13:49
 * @description
 */
@Data
@ToString
@NoArgsConstructor
public class User {
    private String userName;
    private String phoneNo;
    private String idNo;
}
