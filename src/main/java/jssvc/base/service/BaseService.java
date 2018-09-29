package jssvc.base.service;

import java.sql.SQLException;
import jssvc.base.model.Constant;
import java.util.List;

public interface BaseService {


    /***************************************************************************
     * @Description 根据类型和键值获取对应常量
     * @param type 类型、enKey 键值
     * @return user 常量
     * @author redcomet @date 2018-09-29 @reason 新增
     * @throws SQLException
     **************************************************************************/
    String getConstant(String type, String enKey) throws SQLException;

    /***************************************************************************
     * @Description 根据类型和名称值获取对应常量
     * @param type 类型、enKey 键值
     * @return 常量
     * @author redcomet @date 2018-09-29 @reason 新增
     * @throws SQLException
     **************************************************************************/
    String getConstantByName(String type, String name) throws SQLException;
}
