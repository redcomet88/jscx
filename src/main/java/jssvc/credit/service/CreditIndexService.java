package jssvc.credit.service;

import jssvc.base.exception.BusinessException;
import jssvc.credit.model.CreditIndex;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;

import java.util.List;

/**
 * @Description: 信用信息服务
 * @Author: redcomet
 * @Date: 2018-10-16-9:01
 */

public interface CreditIndexService {

    /**
     * @description:获取诚信指标信息
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<jssvc.credit.vo.CreditIndexVo>        
     * @create: 2018/10/18 
     **/
    List<CreditIndexVo> getCreditIndexList(CreditIndexSearchFilter filter);
    
    /**
     * @description:获取诚信指标列表数目
     *
     * @author: redcomet
     * @param: [filter]
     * @return: int        
     * @create: 2018/10/19 
     **/
    int getCreditIndexListCount(CreditIndexSearchFilter filter);

    /**
     * @description:获取诚信指标选项
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<jssvc.credit.vo.CreditIndexVo>
     * @create: 2018/10/28
     **/
    List<CreditIndex> getCreditIndexOption(CreditIndexSearchFilter filter);


    CreditIndexVo getCreditIndex(String id);

    boolean updateCreditIndex(CreditIndex index) throws BusinessException;

    boolean createCreditIndex(CreditIndex index) throws BusinessException;
}
