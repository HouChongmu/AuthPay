package com.yolyn.authpay.db;

import com.yolyn.authpay.db.mapper.UserMapper;
import com.yolyn.authpay.db.pojo.User;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;


/**
 * @author houyl
 * @date 2020/1/30 16:17
 * @description
 */
public class UserMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        User user=userMapper.selectByIdNo("侯允林");
        System.out.println(user);
        sqlSession.close();
    }
}
