package jssvc.base.service.impl;

import jssvc.base.service.BaseService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-09-29-14:20
 */

@Service("baseService")
public class BaseServiceImpl implements BaseService {
    @Override
    public String getConstant(String type, String enKey) {
        return null;
    }

    @Override
    public String getConstantByName(String type, String name) {
        return null;
    }
}
