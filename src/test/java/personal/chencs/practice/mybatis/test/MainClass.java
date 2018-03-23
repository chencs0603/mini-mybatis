package personal.chencs.practice.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import personal.chencs.practice.User;
import personal.chencs.practice.UserMapper;

import java.io.InputStream;

/**
 * @author: chencs
 * @date: 2018/3/23
 * @description:
 */
public class MainClass {

    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper mapper = session.getMapper(UserMapper.class);
            User user = mapper.queryById(100L);
            System.out.println(user);
        } finally {
            session.close();
        }
    }

}
