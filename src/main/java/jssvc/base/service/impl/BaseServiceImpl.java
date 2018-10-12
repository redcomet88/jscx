package jssvc.base.service.impl;

import jssvc.base.dao.ConstantMapper;
import jssvc.base.model.Constant;
import jssvc.base.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-09-29-14:20
 */

@Service("baseService")
public class BaseServiceImpl implements BaseService {
    @Autowired
    private ConstantMapper constantDao;

    @Override
    public String getConstant(String type, String enKey) {
        return null;
    }

    @Override
    public String getConstantByName(String type, String name) {
        return null;
    }

    public List<Constant> getConstantList(String type) {
        List<Constant> constantList = constantDao.selectByType(type);
        return constantList;
    }
}
