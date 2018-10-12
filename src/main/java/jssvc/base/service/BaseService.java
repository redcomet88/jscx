package jssvc.base.service;

import java.sql.SQLException;
import jssvc.base.model.Constant;
import java.util.List;

public interface BaseService {

    /**
     * @description: 
     *
     * @author: redcomet
     * @param: [type, enKey]
     * @return: java.lang.String        
     * @create: 2018/9/29 
     **/
    String getConstant(String type, String enKey) throws SQLException;

   /**
    * @description: 
    *
    * @author: redcomet
    * @param: [type, name]
    * @return: java.lang.String        
    * @create: 2018/9/29 
    **/
    String getConstantByName(String type, String name) throws SQLException;

    /**
     * @description:根据类型获取常量列表
     *
     * @author: redcomet
     * @param: [type]
     * @return: java.util.List<jssvc.base.model.Constant>        
     * @create: 2018/10/12 
     **/
    List<Constant> getConstantList(String type) throws SQLException;
}
