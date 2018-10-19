package jssvc.credit.controller;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.controller.BaseController;
import jssvc.base.enums.ActionType;
import jssvc.base.enums.SortOrder;
import jssvc.base.exception.BusinessException;
import jssvc.base.interceptor.LogFace;
import jssvc.base.model.Constant;
import jssvc.base.service.BaseService;
import jssvc.base.util.*;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.service.CreditReportService;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import jssvc.user.model.InstitutionInfo;
import jssvc.user.model.MenuFunction;
import jssvc.user.model.Role;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * 信用信息Controller
 * @author redcomet
 *
 */
@Controller
public class CreditInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CreditInfoController.class);
    // 平台管理员隐藏姓名
    private final String ADMIN_EN = "administrator";
    // 建议人
    private final String PROPOUNDER = "propounder";
    // 主管总经理
    private final String MANAGER_EN = "manager";
    // 常量表中合理化建议状态对应的角色
    private final String SUGGEST_CONSTANT = "suggestStatus";
    @Autowired
    private CreditIndexService creditIndexService;
    @Autowired
    private CreditReportService creditReportService;
    @Autowired
    private UserService userService;
    @Autowired
    private BaseService baseService;

    private String currentUser;

    /**
     * @description:转入信用事件页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/16 
     **/
    @RequestMapping("creditInfoSercher.do")
    public ModelAndView showSuggestSearch() {
        User user = getSessionUser();
        String userNo = user.getDah();
        String role = null;
        //role是遗留代码
        ModelAndView mv = new ModelAndView();
        mv.setViewName("credit/creditInfoSercher");
        mv.addObject("role", role);
        mv.addObject("userNo", userNo);
        return mv;
    }


    @RequestMapping("showCreditIndex.do")
    public ModelAndView showCreditIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("credit/creditIndexList");
        return mv;
    }

    @ResponseBody
    @RequestMapping("ajax/credit_creditIndexList.do")
    private void creditIndexList(CreditIndexSearchFilter filter) throws BusinessException {
        try {
            filter.setOffset();
            filter.setLimit();
            filter.setLevel(2);  //目前的指标体系只有二级，一级指标类似一个类别，二级指标才有实际意义
            filter.setSortField("sort");
            filter.setSortOrder(SortOrder.ASC.toString());
            List<CreditIndexVo> list = creditIndexService.getCreditIndexList(filter);
            int count = creditIndexService.getCreditIndexListCount(filter);
            //返回数据
            Map<String, Object> result = new HashMap<>();
            result.put("data", list);
            result.put("total", count);
            response.getWriter().write(JSON.Encode(result));
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
