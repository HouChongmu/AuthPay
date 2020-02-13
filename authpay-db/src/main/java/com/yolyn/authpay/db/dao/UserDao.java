package com.yolyn.authpay.db.dao;

import com.yolyn.authpay.db.mapper.UserMapper;
import com.yolyn.authpay.db.pojo.User;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * @author houyl
 * @date 2020/1/30 18:13
 * @description
 */
@Repository
public class UserDao {
    public User selectByUserName(String idNo) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.selectByIdNo(idNo);
        sqlSession.close();
        return user;
    }
}
