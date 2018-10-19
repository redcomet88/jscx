package jssvc.credit.service;

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
}
