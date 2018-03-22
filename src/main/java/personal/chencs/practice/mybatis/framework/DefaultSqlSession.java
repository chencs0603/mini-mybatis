package personal.chencs.practice.mybatis.framework;

import java.lang.reflect.Proxy;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class DefaultSqlSession implements SqlSession {

    private Executor executor;
    private Config config;

    public DefaultSqlSession() {
        this.executor = new DefaultExcutor();
        this.config = new Config();
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return executor.query(statement, parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, new MapperProxy(this, config));
    }

}
