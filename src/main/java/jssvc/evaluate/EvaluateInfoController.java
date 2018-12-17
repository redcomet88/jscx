package jssvc.evaluate;

import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.controller.BaseController;
import jssvc.base.enums.ActionType;
import jssvc.base.enums.SortOrder;
import jssvc.base.exception.BusinessException;
import jssvc.base.interceptor.LogFace;
import jssvc.base.model.ApproveOption;
import jssvc.base.service.BaseService;
import jssvc.base.util.DateUtil;
import jssvc.base.util.FileUtil;
import jssvc.base.util.JSON;
import jssvc.base.util.ResourceUtil;
import jssvc.credit.enums.CreditProcessStatus;
import jssvc.credit.enums.CreditStatusResult;
import jssvc.credit.model.*;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.service.CreditInfoService;
import jssvc.credit.service.CreditReportService;
import jssvc.credit.util.SuggestProcessUtil;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.CreditProcessLogVo;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import jssvc.credit.vo.filter.CreditPeopleSearchFilter;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
import jssvc.user.model.InstitutionInfo;
import jssvc.user.model.MenuFunction;
import jssvc.user.model.Role;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

/**
 * 评测信息Controller
 * @author redcomet
 *
 */
@Controller
public class EvaluateInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(EvaluateInfoController.class);


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
    @RequestMapping("showEnluateInfo.do")
    public ModelAndView showEnluateInfo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("evaluate/evaluteInfo");
        return mv;
    }
}
