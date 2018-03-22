package personal.chencs.practice.mybatis.framework;

/**
 *
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public interface SqlSession {

    <T> T selectOne(String statement, Object parameter);

    <T> T getMapper(Class<T> type);

}
