package personal.chencs.practice.mybatis.framework;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public interface Executor {

    <T> T query(String statement, Object parameter);

}
