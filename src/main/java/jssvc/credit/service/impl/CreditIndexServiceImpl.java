package jssvc.credit.service.impl;

import jssvc.base.constant.ConstantMessage;
import jssvc.base.dao.ConstantMapper;
import jssvc.base.exception.BusinessException;
import jssvc.base.model.Constant;
import jssvc.base.util.JSON;
import jssvc.credit.dao.CreditIndexMapper;
import jssvc.credit.model.CreditIndex;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

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

    @Autowired
    private ConstantMapper constantDao;

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

    @Override
    public CreditIndexVo getCreditIndex(String id){
        logger.info("getCreditIndex begin");
        CreditIndexVo index  = creditIndexDao.selectVoByPrimaryKey(id);
        logger.info("getCreditIndex end");
        return index;
    }

    @Override
    @Transactional
    public boolean updateCreditIndex(CreditIndex index) throws BusinessException {
        boolean flag = true;
        try {
            // 更新指标信息
            creditIndexDao.updateByPrimaryKeySelective(index);
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00010, e);
        }
        return flag;
    }

    @Override
    @Transactional
    public boolean createCreditIndex(CreditIndex index) throws BusinessException {
        boolean flag = true;
        try {
            // 根据传入的指标等级，去常量表找到对应的id值
            // 设置index.id的值
            // 最后更新序列的值
            int level = index.getLevel();
            if(level == 1){
                Constant newId = constantDao.selectByPrimaryKey((long) 4);
                index.setId("X" + newId.getName().trim());       //一级指标的规则是X + 数字
                int sort = Integer.parseInt(newId.getName()) * 1000;   //sort是序号 * 1000
                index.setSort(sort);
                index.setParrentId("-1");
                index.setWeight(0.0);
                index.setCreditAction(" ");

                int nIdNew = Integer.parseInt(newId.getName())  + 1; //更新序列号的新值
                String sIdNew = String.valueOf(nIdNew);
                newId.setName(sIdNew);
                constantDao.updateByPrimaryKey(newId);
            }
            else if(level == 2) {       // 如果是二级指标则根据指标中的一级指标把目前的最大sort取出来
                Constant newId = constantDao.selectByPrimaryKey((long) 3);
                index.setId(newId.getName().trim());
                int sort = creditIndexDao.getMaxSortOfIndex(index.getParrentId());
                index.setSort(sort + 1);     //sort增1
                int nIdNew = Integer.parseInt(newId.getName())  + 1; //更新序列号的新值
                String sIdNew = String.valueOf(nIdNew);
                newId.setName(sIdNew);
                constantDao.updateByPrimaryKey(newId);
            }
            else
                return false;
            // 更新指标信息
            creditIndexDao.insertSelective(index);
            return true;
        } catch (RuntimeException e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new BusinessException(ConstantMessage.ERR00010, e);
        }
    }
}
