package personal.chencs.practice.mybatis.framework;

import personal.chencs.practice.UserMapper;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;
    private Config config;

    public MapperProxy(SqlSession sqlSession, Config config) {
        this.sqlSession = sqlSession;
        this.config = config;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean mapperBean = config.getMapper("UserDao.xml");
        if (method.getDeclaringClass().getName().equals(mapperBean.getNameSpace())) {
            String sql = mapperBean.getMethodSqlMapping().get(method.getName());

            return sqlSession.selectOne(sql, String.valueOf(args[0]));
        }

        return null;
    }

}
