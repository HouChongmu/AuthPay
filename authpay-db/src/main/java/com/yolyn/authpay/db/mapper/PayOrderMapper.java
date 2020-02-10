package com.yolyn.authpay.db.mapper;

import com.yolyn.authpay.db.pojo.PayOrder;

/**
 * @author houyl
 * @date 2020/2/8 20:56
 * @description 订单表Mapper
 */
public interface PayOrderMapper {
    Integer insertPayOrder(PayOrder payOrder);
    PayOrder selectByOrderNo(String orderNo);
}
