package com.yolyn.authpay.db.dao;

import com.yolyn.authpay.db.mapper.PayOrderMapper;
import com.yolyn.authpay.db.pojo.PayOrder;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * @author houyl
 * @date 2020/2/9 12:13
 * @description
 */
@Repository
public class PayOrderDao {
    public Integer insertPayOrder(PayOrder payOrder){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        PayOrderMapper payOrderMapper=sqlSession.getMapper(PayOrderMapper.class);
        int num=payOrderMapper.insertPayOrder(payOrder);
        sqlSession.commit();
        sqlSession.close();
        return  num;
    }
}
