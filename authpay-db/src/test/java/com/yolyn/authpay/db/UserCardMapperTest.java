package com.yolyn.authpay.db;

import com.yolyn.authpay.db.mapper.UserCardMapper;
import com.yolyn.authpay.db.pojo.UserCard;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


/**
 * @author houyl
 * @date 2020/1/29 18:00
 * @description
 */
public class UserCardMapperTest {
    @Test
    public void userCardTest(){
            SqlSession sqlSession= SqlSessionUtil.getSqlSession();
            UserCardMapper userCardMapper=sqlSession.getMapper(UserCardMapper.class);
            UserCard userCard=userCardMapper.selectByCardNo("6228213179024293876");
            System.out.println(userCard);
    }
}
