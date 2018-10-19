package jssvc.base.controller;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.enums.ActionType;
import jssvc.base.exception.BusinessException;
import jssvc.base.service.LogService;
import jssvc.base.util.JSON;
import jssvc.base.vo.LogVo;
import jssvc.base.vo.filter.LogSearchFilter;
import jssvc.user.model.InstitutionInfo;
import jssvc.user.model.MenuFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;


@Controller
public class LogController extends BaseController {

    @Autowired
    @Qualifier("logService")
    private LogService logService;

    /**
     * @description:转入日志管理页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/15 
     **/
    @ResponseBody
    @RequestMapping("showLogList.do")
    private ModelAndView showWpTypeList() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.LOG_LIST);
        return mv;
    }

   /**
    * @description:初始化日志管理页面
    *
    * @author: redcomet
    * @param: [id]
    * @return: void        
    * @create: 2018/10/15 
    **/
    @ResponseBody
    @RequestMapping("ajax/log_initLogList.do")
    public void initLogList(String id) throws BusinessException {
        try {
            // 取得机构列表
            List<InstitutionInfo> jgxx = getJgxxList();
            // 取得功能权限列表
            List<MenuFunction> menuFunctions = getMenuFunction(id);
            Boolean searchFlag = false;
            for (int i = 0; i < menuFunctions.size(); i++) {
                switch (ActionType.valueOf(menuFunctions.get(i).getFunctionAction())) {
                case log_listLog:
                    searchFlag = true;
                    break;
                default:
                    break;
                }
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_JGH_LIST, jgxx);
            hashmap.put(ConstantKey.SEARCH_FLAG, searchFlag);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }

    }

   /**
    * @description:日志查询
    *
    * @author: redcomet
    * @param: [filter]
    * @return: void        
    * @create: 2018/10/15 
    **/
    @ResponseBody
    @RequestMapping("ajax/log_listLog.do")
    public void listLog(LogSearchFilter filter) throws BusinessException {
        try {
            // 查询条件
            filter.setOffset();
            filter.setLimit();

            // 取得日志列表
            List<LogVo> result = logService.getLogs(filter);
            //屏蔽password
            String[] keyWords={"password=","oldPwd=","newPwd="}; 
            for(String key:keyWords){
                for(LogVo log :result){
                    String obj=log.getObject();
                    String[] objStrs=obj.split(key);
                    if(null==objStrs || objStrs.length < 2)
                        continue;
                    obj=objStrs[0];
                    for(int i=1;i<objStrs.length;i++){
                        if(-1 == objStrs[i].indexOf(","))
                            obj+=key+"******";
                        else
                            obj+=key+"******"+objStrs[i].substring(objStrs[i].indexOf(","));
                    }
                    log.setObject(obj);
                }
            }
            // 取得日志总件数
            long count = logService.getLogsCount(filter);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put("data", result);
            hashmap.put("total", count);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
}
