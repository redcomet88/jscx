package jssvc.credit.service;

import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description: 诚信信息服务
 * @Author: redcomet
 * @Date: 2018-10-22-16:08
 */

public interface CreditInfoService {
    /**
     * @description:根据ID来查询诚信事件
     *
     * @author: redcomet
     * @param: [id]
     * @return: jssvc.credit.model.CreditProcess        
     * @create: 2018/10/24 
     **/
    CreditProcess selectByPrimaryKey(int id);
    
    /**
     * @description: 查询诚信事件的编号(同时更新)
     *
     * @author: redcomet
     * @param: [str, string]
     * @return: int
     * @create: 2018/10/22
     **/
    int selectFlagNumber(String str, String string);

    /**
     * @description:插入流转日志
     *
     * @author: redcomet
     * @param: [suggestProcessLog]
     * @return: int
     * @create: 2018/10/23
     **/
    int insertProcessLog(CreditProcessLog suggestProcessLog);

    /**
     * @description:创建诚信事件
     *
     * @author: redcomet
     * @param: [suggestInfo]
     * @return: java.lang.String
     * @create: 2018/10/23
     **/
    String createCreditInfo(CreditProcess suggestInfo) throws SQLException;

    /**
     * @description:创建附件
     *
     * @author: redcomet
     * @param: [before]
     * @return: java.lang.Boolean
     * @create: 2018/10/23
     **/
    Boolean createAttachment(CreditAttachment before) throws SQLException;

    /**
     * @description:获取诚信事件流程列表
     *
     * @author: redcomet
     * @param: [filter]
     * @return: java.util.List<SuggestInfoVo>        
     * @create: 2018/10/24 
     **/
    List<CreditProcessVo> getCreditInfoList(CreditProcessSearchFilter filter);

    /**
     * @description:获取诚信事件流程列表计数
     *
     * @author: redcomet
     * @param: [filter]
     * @return: int        
     * @create: 2018/10/24 
     **/
    int getCreditInfoListCount(CreditProcessSearchFilter filter);


}