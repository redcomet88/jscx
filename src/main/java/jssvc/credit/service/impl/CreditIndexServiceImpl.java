package jssvc.credit.service.impl;

import jssvc.credit.dao.CreditIndexMapper;
import jssvc.credit.model.CreditIndex;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: redcomet
 * @Date: 2018-10-17-8:40
 */

@Service("creditIndexService")
public class CreditIndexServiceImpl implements CreditIndexService {
    @Autowired
    private CreditIndexMapper creditIndexDao;

    private static Logger  logger = LoggerFactory.getLogger(CreditIndexServiceImpl.class);

    @Override
    public List<CreditIndexVo> getCreditIndexList(CreditIndexSearchFilter filter) {
        logger.info("getCreditIndexList begin");
        List<CreditIndexVo> list  = creditIndexDao.getCreditIndexList(filter);
        logger.info("getCreditIndexList end");
        return list;
    }

    @Override
    public int getCreditIndexListCount(CreditIndexSearchFilter filter) {
        logger.info("getCreditIndexListCount begin");
        int count = creditIndexDao.getCreditIndexListCount(filter);
        logger.info("getCreditIndexListCount end");
        return count;
    }

    @Override
    public List<CreditIndex> getCreditIndexOption(CreditIndexSearchFilter filter) {
        logger.info("getCreditIndexList begin");
        List<CreditIndex> list  = creditIndexDao.getCreditIndexOption(filter);
        logger.info("getCreditIndexList end");
        return list;
    }
}
