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
}
