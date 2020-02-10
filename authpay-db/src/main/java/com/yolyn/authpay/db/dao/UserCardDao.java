package com.yolyn.authpay.db.dao;

import com.yolyn.authpay.db.mapper.UserCardMapper;
import com.yolyn.authpay.db.mapper.UserMapper;
import com.yolyn.authpay.db.pojo.User;
import com.yolyn.authpay.db.pojo.UserCard;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

/**
 * @author houyl
 * @date 2020/1/30 18:13
 * @description
 */
@Repository
public class UserCardDao {
    /**
     *
     * @param cardNo
     * @return
     */
    public UserCard selectByCardNo(String cardNo) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserCardMapper userCardMapper = sqlSession.getMapper(UserCardMapper.class);
        UserCard userCard = userCardMapper.selectByCardNo(cardNo);
        return userCard;
    }

    /**
     * 更新余额
     * @param userCard
     * @return
     */
    public Integer updateUserCardBalance(UserCard userCard){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserCardMapper userCardMapper=sqlSession.getMapper(UserCardMapper.class);
        int num=userCardMapper.updateUserCardBalance(userCard);
        sqlSession.close();
        return num;
}
}
