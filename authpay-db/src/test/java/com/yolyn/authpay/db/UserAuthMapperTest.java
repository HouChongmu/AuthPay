package com.yolyn.authpay.db;

import com.yolyn.authpay.db.mapper.UserAuthMapper;
import com.yolyn.authpay.db.pojo.UserAuth;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author houyl
 * @date 2020/1/30 18:01
 * @description
 */
public class UserAuthMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserAuthMapper userAuthMapper=sqlSession.getMapper(UserAuthMapper.class);
//        UserAuth userAuth=userAuthMapper.selectUserAuth("侯允林");
//        System.out.println(userAuth);
    }
    @Test
    public void insert(){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserAuthMapper userAuthMapper=sqlSession.getMapper(UserAuthMapper.class);
        UserAuth userAuth=new UserAuth();
        userAuth.setUserName("婧婧");
        userAuth.setAuthStatus("0");
        userAuth.setPhoneNo("13866077233");
        userAuth.setIdNo("123456");
        userAuth.setCardNo("465789");
        userAuthMapper.insertUserAuth(userAuth);
        sqlSession.commit();
        sqlSession.close();
    }
}
