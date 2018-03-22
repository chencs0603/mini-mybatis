package personal.chencs.practice.mybatis.framework;

import personal.chencs.practice.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class DefaultExcutor implements Executor {

    @Override
    public <T> T query(String statement, Object parameter) {
        Connection connection= getConnection();
        ResultSet set =null;
        PreparedStatement pre =null;
        try {
            pre = connection.prepareStatement(statement);
            //设置参数
            pre.setString(1, parameter.toString());
            set = pre.executeQuery();

            User u = new User();
            //遍历结果集
            while(set.next()){
                u.setId(Long.parseLong(set.getString(1)));
                u.setName(set.getString(2));
                u.setAge(Integer.parseInt(set.getString(3)));
            }
            return (T) u;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try{
                if(set!=null){
                    set.close();
                }if(pre!=null){
                    pre.close();
                }if(connection!=null){
                    connection.close();
                }
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
        return null;
    }

    private Connection getConnection() {
        try {
            Connection connection = new Config().getConnection("mybatis-config.xml");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
