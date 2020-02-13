package com.yolyn.authpay.db.dao;

import com.yolyn.authpay.db.mapper.UserAuthMapper;
import com.yolyn.authpay.db.pojo.UserAuth;
import com.yolyn.authpay.db.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

/**
 * @author houyl
 * @date 2020/1/30 18:13
 * @description
 */
@Repository
public class UserAuthDao {
    /**
     * select
     * @param idNo
     * @param cardNo
     * @return
     */
    public UserAuth selectByCardNo(String idNo,String cardNo) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        UserAuthMapper userAuthMapper = sqlSession.getMapper(UserAuthMapper.class);
        UserAuth userAuth = userAuthMapper.selectUserAuth(idNo,cardNo);
        sqlSession.close();
        return userAuth;
    }

    /**
     * insert
     * @param userAuth
     * @return
     */
    public Integer insertUserAuth(UserAuth userAuth){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserAuthMapper userAuthMapper = sqlSession.getMapper(UserAuthMapper.class);
        int num=userAuthMapper.insertUserAuth(userAuth);
        sqlSession.commit();
        sqlSession.close();
        return num;
    }

    /**
     * update
     * @param userAuth
     * @return
     */
    public Integer updateUserAuth(UserAuth userAuth){
        SqlSession sqlSession=SqlSessionUtil.getSqlSession();
        UserAuthMapper userAuthMapper = sqlSession.getMapper(UserAuthMapper.class);
        int num=userAuthMapper.updateUserAuth(userAuth);
        sqlSession.commit();
        sqlSession.close();
        return num;
    }
}
