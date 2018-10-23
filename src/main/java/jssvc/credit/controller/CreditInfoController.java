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
import jssvc.credit.enums.CreditProcessStatus;
import jssvc.credit.enums.CreditStatusResult;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.service.CreditInfoService;
import jssvc.credit.service.CreditReportService;
import jssvc.credit.util.SuggestProcessUtil;
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
    private CreditInfoService creditInfoService;
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
    
    /**
     * @description:转入信用指标管理页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/22 
     **/
    @RequestMapping("showCreditIndex.do")
    public ModelAndView showCreditIndex() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("credit/creditIndexList");
        return mv;
    }

    /**
     * @description:信用指标列表查询
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void        
     * @create: 2018/10/22 
     **/
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

    /**
     * @description: 诚信事件新增/编辑页面展示
     *
     * @author: redcomet
     * @param: [flag, id, suggestCode]
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/22
     **/
    @LogFace
    @ResponseBody
    @RequestMapping("addCreditInfoApply.do")
    private ModelAndView addCreditInfoApply(String flag, String id, String suggestCode) throws BusinessException {
        try {
            // 跳转到合理化建议信息更新页面
            ModelAndView mv = new ModelAndView();
            mv.setViewName(ConstantKey.CREDIT_INFO_APPLY);
            mv.addObject("flag", flag);
            // TODO 这两行先注释起来，我先把页面改好，然后再做后台服务
            String suggestBh = String.valueOf(creditInfoService.selectFlagNumber("suggestBh", "suggestBh"));
            mv.addObject("suggestBh", suggestBh);
            String fbsj = DateUtil.getSimpleDateString(new Date());
            mv.addObject("applytime", fbsj);
            return mv;
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        }
    }

    /**
     * @description:插入诚信流程日志
     *
     * @author: redcomet
     * @param: [suggestId, processingUser, nextUser, handleTime, aproveStatus, oprationDetail, oprationResult]
     * @return: boolean        
     * @create: 2018/10/23 
     **/
    private boolean insertProcessLog(String processid, String processingUser, String nextUser, Date handleTime, String aproveStatus, String oprationDetail,
                                     String oprationResult) {
        CreditProcessLog log = new CreditProcessLog();
        log.setProcessid(processid);
        log.setHandleTime(handleTime);
        log.setNextUser(nextUser);
        log.setApproveStatus(aproveStatus);
        log.setOprationDetail(oprationDetail);
        log.setOprationResult(oprationResult);
        log.setProcessingUser(processingUser);
        int result = creditInfoService.insertProcessLog(log);
        return result == 0;
    }

    /**
     * @description:新增诚信事件申请
     *
     * @author: redcomet
     * @param: [suggestFormTransfer, step]
     * @return: void        
     * @create: 2018/10/23 
     **/
    @SuppressWarnings("rawtypes")
    @RequestMapping("ajax/suggestInfo_addCreditInfo.do")
    @ResponseBody
    public void addCreditInfo(String suggestFormTransfer, String step) throws BusinessException {
        try {
            String status = SuggestProcessUtil.getNextProcessStatus(CreditProcessStatus.suggestionApplyStart.getId(), CreditStatusResult.pass.getId(), step);
            Map suggest = (Map) JSON.Decode(suggestFormTransfer);
            User user = getSessionUser();
            if (suggest != null && !suggest.isEmpty()) {
                CreditProcess suggestInfo = new CreditProcess();
                if (suggest.get("suggestTitle") != null) {
                    suggestInfo.setCreditTitle(suggest.get("suggestTitle").toString());
                }
                if (suggest.get("suggestContent") != null) {
                    suggestInfo.setCreditContent(suggest.get("suggestContent").toString());
                }
                if (suggest.get("code") != null) {
                    suggestInfo.setCode(suggest.get("code").toString());
                }
                if (suggest.get("applyTime") != null) {
                    suggestInfo.setCreateTime(DateUtil.parser(suggest.get("applyTime").toString()));
                }
                suggestInfo.setUploadUser(user.getDah());
                suggestInfo.setUploadDept(getSessionJgh());
                suggestInfo.setStatus(status);
                // 找出所有下一步处理人员的名单，放到current user中
                List<User> users = userService.getUsersByRole("82");
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < users.size(); i++) {
                    String flag = i == users.size() - 1 ? "" : ",";
                    String current = users.get(i).getDah();
                    sb.append(current).append(flag);
                }
                if ("admin".equals(user.getDah())) {
                    currentUser = "admin";
                } else {
                    currentUser = sb.toString();
                }
               //suggestInfo.setManagerUser(currentUser);
                suggestInfo.setCurrentuser(currentUser);
                creditInfoService.createCreditInfo(suggestInfo);
               
                insertProcessLog(suggestInfo.getCode(), PROPOUNDER, ADMIN_EN, new Date(), CreditProcessStatus.suggestionApplyStart.getId(), "诚信事件提交",
                        CreditStatusResult.pass.getName());
            }
            response.getWriter().write("success");
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e1) {
            throw new BusinessException(ConstantMessage.ERR00005, e1);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (Exception e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        }
    }

    
}
