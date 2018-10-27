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
import jssvc.credit.model.CreditAttachment;
import jssvc.credit.model.CreditProcess;
import jssvc.credit.model.CreditProcessLog;
import jssvc.credit.service.CreditIndexService;
import jssvc.credit.service.CreditInfoService;
import jssvc.credit.service.CreditReportService;
import jssvc.credit.util.SuggestProcessUtil;
import jssvc.credit.vo.CreditIndexVo;
import jssvc.credit.vo.CreditProcessVo;
import jssvc.credit.vo.filter.CreditIndexSearchFilter;
import jssvc.credit.vo.filter.CreditProcessSearchFilter;
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
            User user = getSessionUser();
            filter.setOffset();
            filter.setLimit();
            filter.setDah(user.getDah());
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

    /**
     * @description:初始化诚信事件查询权限
     *
     * @author: redcomet
     * @param: [id]
     * @return: void        
     * @create: 2018/10/24 
     **/
    @RequestMapping("ajax/suggest_initSearch.do")
    @ResponseBody
    private void initCreditSearch(String id) throws BusinessException {
        try {
            // 取得功能权限列表
            List<MenuFunction> menuFunctions = getMenuFunction(id);
            // 查询采购申请权限
            Boolean searchFlag = false;
            // 新增采购申请权限
            Boolean addFlag = false;
            // 打印购申请权限
            Boolean printFlag = false;
            // 查看申请权限
            Boolean detailFlag = false;
            // 判断登录者是否具有新增采购申请权限
            for (int i = 0; i < menuFunctions.size(); i++) {
                switch (ActionType.valueOf(menuFunctions.get(i).getFunctionAction())) {
                    case addSuggestInfoApply:
                        addFlag = true;
                        break;
                    default:
                        break;
                }
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.SEARCH_FLAG, searchFlag);
            hashmap.put(ConstantKey.ADD_FLAG, addFlag);
            hashmap.put("detailFlag", detailFlag);
            hashmap.put("printFlag", printFlag);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:获得事件流程的状态表
     *
     * @author: redcomet
     * @param: []
     * @return: void        
     * @create: 2018/10/24 
     **/
    @ResponseBody
    @RequestMapping("ajax/suggest_getSuggestProcessStatusList.do")
    private void getSuggestProcessStatusList() throws BusinessException {
        try {
            List<HashMap<String, String>> suggestProcessStatusList = CreditProcessStatus.getSuggestProcessStatusList();
            String json = JSON.Encode(suggestProcessStatusList);
            response.getWriter().write(json);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:诚信事件列表查询
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void        
     * @create: 2018/10/24 
     **/
    @ResponseBody
    @RequestMapping("ajax/suggest_suggestInfoList.do")
    private void creditInfoList(CreditProcessSearchFilter filter) throws BusinessException, SQLException {
        try {
            User user = getSessionUser();
            // 设置查询条件
            filter.setOffset();
            filter.setLimit();
            filter.setLoginDah(user.getDah());
            //filter.setSuggestEnd(true);
            //filter.setSuggestStart(true);
            //filter.setSuggestApply(true);
            filter.setSortField("create_Time");
            filter.setSortOrder(SortOrder.DESC.toString());
            //filter.setCreateDateEnd(StringUtil.isEmpty(filter.getCreateDateEnd()) ? null
            //       : DateUtil.getSimpleDateStringYYYYMMDDHHMM(DateUtil.getTheEndTimeOfDate(DateUtil.parser(filter.getCreateDateEnd()))).toString());
            List<Role> roles = userService.getRolesByDah(user.getDah());
            filter = setSuggestFielterByRoles(roles, filter);
            List<CreditProcessVo> list = creditInfoService.getCreditInfoList(filter);
            int count = creditInfoService.getCreditInfoListCount(filter);
            // 跳转到合理化建议信息更新页面
            Map<String, Object> result = new HashMap<String, Object>();
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
     * @description:转入诚信事件审批页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/25 
     **/
    @RequestMapping("showCreditUndone.do")
    private ModelAndView showSuggestUndone() throws SQLException {
        User user = getSessionUser();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("credit/creditUndone");
        List<Role> roles = userService.getRolesByDah(user.getDah());
        //返回诚信事件管理员的权限
        for (Role role : roles) {
            if (role.getId() == 82) {
                mv.addObject("role", "administrator");
            }
        }
        return mv;
    }
    
    /**
     * @description:显示诚信代办事件
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void        
     * @create: 2018/10/24 
     **/
    @ResponseBody
    @RequestMapping("ajax/suggest_showSuggestInfoUndoneList.do")
    private void showCreditInfoUndoneList(CreditProcessSearchFilter filter) throws BusinessException {
        try {
            User user = getSessionUser();
            // 设置查询条件
            filter.setOffset();
            filter.setLimit();
            //filter.setSuggestEnd(false);
            //filter.setSuggestStart(true);
            //filter.setSuggestApply(true);
            filter.setCurrentuser(user.getDah());
            filter.setLoginDah(user.getDah());
            filter.setSortField("opt_Time");
            filter.setSortOrder(SortOrder.DESC.toString());
            List<CreditProcessVo> list = creditInfoService.getCreditInfoList(filter);
            int count = creditInfoService.getCreditInfoListCount(filter);
            // 跳转到合理化建议信息更新页面
            Map<String, Object> result = new HashMap<String, Object>();
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
     * @description:查询诚信事件的角色限制
     *
     * @author: redcomet
     * @param: [roles, filter]
     * @return: SuggestInfoSearchFilter        
     * @create: 2018/10/24 
     **/
    private CreditProcessSearchFilter setSuggestFielterByRoles(List<Role> roles, CreditProcessSearchFilter filter) {
       //目前未做任何控制
        return filter;
    }
    
    /**
     * @description:获得部门列表
     *
     * @author: redcomet
     * @param: []
     * @return: void
     * @create: 2018/10/24
     **/
    @RequestMapping("ajax/suggestInfo_selectInstitutionList.do")
    @ResponseBody
    public void getInstitutionList() {
        try {
            String dah = "admin";
            String jgh = getSessionJgh();
            List<InstitutionInfo> list = userService.getInstitutionInfos(dah, jgh);
            response.getWriter().write(JSON.Encode(list));
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String strs = sw.toString();
            logger.error(strs);
        }
    }

    @RequestMapping("showSuggestAttachmentsUploadPage.do")
    public String showCreditAttachmentsUploadPage() {
        return "credit/creditAttachmentsUpload";
    }

    @RequestMapping("findSuggestAttachmentsUploadPage.do")
    public String findAttachment() {
        return "credit/creditAttachmentList";
    }

    /**
     * @description:显示诚信事件编辑/显示 页面
     *
     * @author: redcomet
     * @param: [flag, id, suggestCode]
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/24 
     **/
    @ResponseBody
    @RequestMapping("showSuggestInfoApply.do")
    private ModelAndView showSuggestInfoApply(String flag, String id, String suggestCode) throws BusinessException {
        try {
            User user = getSessionUser();
            // 跳转到合理化建议信息更新页面
            ModelAndView mv = new ModelAndView();
            mv.setViewName(ConstantKey.CREDIT_INFO_APPLY);
            mv.addObject("flag", flag);
            if (!"add".equals(flag)) {
                CreditProcess suggestInfo = creditInfoService.selectByPrimaryKey(Integer.valueOf(id));
                mv.addObject("id", id);
                mv.addObject("suggestTitle", suggestInfo.getCreditTitle());
                mv.addObject("suggestContent", suggestInfo.getCreditContent());
                mv.addObject("suggestBh", suggestInfo.getCode());
                mv.addObject("applytime", DateUtil.getSimpleDateTimeString(suggestInfo.getCreateTime()));
                mv.addObject("applystatus", suggestInfo.getStatus());
                mv.addObject("applybank", suggestInfo.getUploadDept());
                mv.addObject("maindepartment", suggestInfo.getUploadDept());
                mv.addObject("minordepartment", suggestInfo.getUploadDept());
                if (user.getDah().equals(suggestInfo.getUploadUser())) {
                    mv.addObject("roleName", "suggestor");
                }
               /* if (suggestInfo.getHandleTime() != null) {
                    mv.addObject("handleTime", DateUtil.getSimpleDateTimeString(suggestInfo.getHandleTime()));
                }
                if (SuggestProcessStatus.departmentAcceptStart.getId().equals(suggestInfo.getApplyStatus()) && suggestInfo.getHandleTime() == null) {
                    // mv.addObject("handleTime", suggestInfo.getHandleTime());
                    // mv.addObject("status", suggestInfo.getApplyStatus());
                    mv.addObject("handleTime", DateUtil.getSimpleDateTimeString(new Date()));
                    // mv.addObject("deadline", DateUtil.getSimpleDateTimeString(DateUtil.addDay(new Date(), 30)));
                    mv.addObject("deadline", suggestInfo.getDeadline());
                } else if (SuggestProcessStatus.adminAccept.getId().equals(suggestInfo.getApplyStatus()) && suggestInfo.getHandleTime() == null) {
                    if (null != suggestInfo.getDeadline()) {
                        mv.addObject("deadline", suggestInfo.getDeadline());
                    }

                } else {
                    // mv.addObject("status", suggestInfo.getApplyStatus());

                    // mv.addObject("handleTime", DateUtil.getSimpleDateTimeString(suggestInfo.getHandleTime()));
                    if (null != suggestInfo.getDeadline()) {
                        mv.addObject("deadline", suggestInfo.getDeadline());
                    }
                }*/

                //mv.addObject("signLeader", suggestInfo.getSignLeader());
                //mv.addObject("signOpinion", suggestInfo.getSignOpinion());
                mv.addObject("column1", suggestInfo.getColumn1());
                //mv.addObject("whetherReasonable", suggestInfo.getWhetherReasonable());
                //mv.addObject("adoptJudge", suggestInfo.getWhetherAccept());
                //mv.addObject("specificInfo", suggestInfo.getSpecificInfo());
                String handleResult2 = "";
                //if (!StringUtil.isEmpty(suggestInfo.getHandleResult())) {
                //    handleResult2 = suggestInfo.getHandleResult().replaceAll("[\n,\r,\r\n]", "&#10;");
                //}
                mv.addObject("handleResult2", handleResult2);
                //mv.addObject("handleResult", suggestInfo.getHandleResult());
                mv.addObject("column2", suggestInfo.getColumn2());
                //mv.addObject("leaderIndicate", suggestInfo.getLeaderIndicate());
                //mv.addObject("currentUser", suggestInfo.getCurrentUser());
                //mv.addObject("statusflag", suggestInfo.getStatusFlag());
            } else {
                String suggestBh = String.valueOf(creditInfoService.selectFlagNumber("suggestBh", "suggestBh"));
                mv.addObject("suggestBh", suggestBh);
                String fbsj = DateUtil.getSimpleDateString(new Date());
                mv.addObject("applytime", fbsj);
            }
            return mv;
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        }
    }
    
    /**
     * @description:上传诚信事件附件方法
     *
     * @author: redcomet
     * @param: [fileupload, suggestAttachmentType]
     * @return: void        
     * @create: 2018/10/23 
     **/
    @ResponseBody
    @RequestMapping("suggestbh_uploadSuggestAttachment.do")
    public void uploadSuggestAttachment(@RequestParam("Fdata") MultipartFile fileupload, CreditAttachment suggestAttachmentType) throws BusinessException {
        response.setHeader("content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String accessibleFiles = ResourceUtil.getText("event_fileType");
        try {
            CreditAttachment before = new CreditAttachment();
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            // 文件是否过大
            long fileSize = Long.valueOf(ResourceUtil.getText("event_attachmentSize"));
            boolean fileSizeAllowed = fileupload.getSize() <= fileSize;
            // 取得文件后缀
            String fileName = fileupload.getOriginalFilename();
            String[] arr = fileName.split(".");
            String fileSuffix = SystemConstant.BLANK;
            if (arr.length > 0) {
                fileSuffix = arr[arr.length - 1];
            }
            // 该文件类型是否可以上传
            boolean accessible = accessibleFiles.indexOf(fileSuffix) >= 0;
            // 文件是否为空
            boolean isNotEmpty = fileupload.isEmpty() == false;
            if (isNotEmpty && accessible && fileSizeAllowed) {
                InputStream is = fileupload.getInputStream();
                // 指定上传地址
                String rootPath = request.getSession().getServletContext().getRealPath("/upload");
                // 创建输出流对象
                String webFileName = FileUtil.getNewFileName(fileName);
                // 创建项目目录
                String subPath = "/upload/creditAttachment";
                String path = rootPath + "/creditAttachment";
                File fold = new File(path);
                // 判断目录是否存在
                if (!fold.exists() && !fold.isDirectory()) {
                    fold.mkdirs();
                }
                // 获取输入流
                byte[] buffer = new byte[1024];
                int length = 0;

                OutputStream os = new FileOutputStream(new File(path, webFileName));
                // 开始上传
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                // 一次关闭流
                os.close();
                is.close();
                before.setDescription(suggestAttachmentType.getDescription());
                before.setUploadDah(getSessionUser().getDah());
                before.setFileName(fileName);
                before.setFilePath(subPath);
                before.setWebFileName(webFileName);
                before.setUploadTime(new Date());
                before.setSuggestbh(suggestAttachmentType.getSuggestbh());
                before.setUploadName(getSessionUser().getYgxm());
                creditInfoService.createAttachment(before);
                hashmap.put("status", ConstantKey.SUCCESS);
                hashmap.put("message", "上传附件成功");
            } else if (!accessible) {
                hashmap.put("status", ConstantKey.FAIL);
                hashmap.put("message", "系统不支持上传该类型文件！");
            } else if (!fileSizeAllowed) {
                hashmap.put("status", ConstantKey.FAIL);
                hashmap.put("message", "您上传的文件不能超过10M！");
            } else if (!isNotEmpty) {
                hashmap.put("status", ConstantKey.FAIL);
                hashmap.put("message", "还没有选择文件");
            }
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (FileNotFoundException e) {
            throw new BusinessException(ConstantMessage.ERR00007, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        }
    }

    /**
     * @description:获取附件权限
     *
     * @author: redcomet
     * @param: [suggestCode]
     * @return: void        
     * @create: 2018/10/24 
     **/
    @RequestMapping(value = "getSuggestAttachmentPermission.do")
    @ResponseBody
    public void suggetAttachmentPermissionControl(String suggestCode) throws BusinessException {

        try {

            Map<String, Object> permissionMap = new HashMap<>();

            String advicer = "true";
            String officer = "true";
            String governor = "false";   //隐藏这个
            //增加对权限的控制
            permissionMap.put("advicer", advicer);
            permissionMap.put("officer", officer);
            permissionMap.put("governor", governor);
            permissionMap.put("deletePermission", 1);
            permissionMap.put("downloadPermission", 1);
            permissionMap.put("previewPermission", 1);

            response.getWriter().write(JSON.Encode(permissionMap));
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
}
