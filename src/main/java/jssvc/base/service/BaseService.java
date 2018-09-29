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
}
