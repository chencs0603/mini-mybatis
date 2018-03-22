package personal.chencs.practice;

import personal.chencs.practice.mybatis.framework.DefaultSqlSession;
import personal.chencs.practice.mybatis.framework.SqlSession;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class MainClass {

    public static void main(String[] args) {
        SqlSession sqlSession = new DefaultSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.queryById(100L);
        System.out.println(user);
    }

}
