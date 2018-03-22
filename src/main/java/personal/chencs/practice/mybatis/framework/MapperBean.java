package personal.chencs.practice.mybatis.framework;

import java.util.Map;

/**
 *
 *
 * @author: chencs
 * @date: 2018/3/22
 * @description:
 */
public class MapperBean {

    private String nameSpace;
    private Map<String, String> methodSqlMapping;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public Map<String, String> getMethodSqlMapping() {
        return methodSqlMapping;
    }

    public void setMethodSqlMapping(Map<String, String> methodSqlMapping) {
        this.methodSqlMapping = methodSqlMapping;
    }

}
