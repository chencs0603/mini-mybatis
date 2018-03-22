package personal.chencs.practice.mybatis.framework;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class Config {

    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    public MapperBean getMapper(String mapperLocation) {
        MapperBean mapper = new MapperBean();
        try{
            InputStream stream = loader.getResourceAsStream(mapperLocation);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();

            String nameSpace = root.attributeValue("namespace").trim();
            mapper.setNameSpace(nameSpace);

            Map<String, String> methodSqlMapping = new HashMap<>();
            for(Iterator rootIter = root.elementIterator(); rootIter.hasNext();) {
                Element e = (Element) rootIter.next();
                String method = e.attributeValue("id").trim();
                String sql = e.getText().trim();

                methodSqlMapping.put(method, sql);
            }
            mapper.setMethodSqlMapping(methodSqlMapping);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return mapper;
    }

    public Connection getConnection(String configLocation) {
        try {
            InputStream stream = loader.getResourceAsStream(configLocation);
            SAXReader reader = new SAXReader();
            Document document = reader.read(stream);
            Element root = document.getRootElement();
            return evalDataSource(root);
        } catch (Exception e) {
            throw new RuntimeException("error occured while evaling xml " + configLocation);
        }
    }

    private  Connection evalDataSource(Element node) throws ClassNotFoundException {
        if (!node.getName().equals("database")) {
            throw new RuntimeException("root should be <database>");
        }
        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        //获取属性节点
        for (Object item : node.elements("property")) {
            Element i = (Element) item;
            String value = getValue(i);
            String name = i.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property> should contain name and value");
            }
            //赋值
            switch (name) {
                case "url" : url = value; break;
                case "username" : username = value; break;
                case "password" : password = value; break;
                case "driverClassName" : driverClassName = value; break;
                default : throw new RuntimeException("[database]: <property> unknown name");
            }
        }

        Class.forName(driverClassName);
        Connection connection = null;
        try {
            //建立数据库链接
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    //获取property属性的值,如果有value值,则读取 没有设置value,则读取内容
    private  String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }


}
