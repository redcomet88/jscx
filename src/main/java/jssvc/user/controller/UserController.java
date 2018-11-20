package jssvc.user.controller;

import flexjson.JSONException;
import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.controller.BaseController;
import jssvc.base.enums.ActionType;
import jssvc.base.exception.BusinessException;
import jssvc.base.interceptor.LogFace;
import jssvc.base.listener.LoginListener;
import jssvc.base.enums.Sex;
import jssvc.base.model.Constant;
import jssvc.base.util.JSON;
import jssvc.base.util.MD5;
import jssvc.base.util.TreeUtil;
import jssvc.base.util.StringUtil;

import jssvc.user.enums.UserStatus;
import jssvc.user.model.*;
import jssvc.user.model.filter.UserSearchFilter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.*;

import static jssvc.base.enums.ActionType.role_deleteRole;
import static jssvc.base.enums.ActionType.role_saveRole;

/**
 * @Description: 用户类控制器
 * @Author: redcomet
 * @Date: 2018-10-02-15:17
 */

@Controller
public class UserController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * @description: 用户登录
     *
     * @author: redcomet
     * @param: [dah, password, jgh]
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/10
     **/
    @ResponseBody
    @RequestMapping("login.do")
    private ModelAndView login(@RequestParam(value = "loginName", required = false) String dah,
                           @RequestParam(value = "password", required = false ) String password,
                           @RequestParam(value = "jgh", required = false) String jgh) throws BusinessException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        //logger.info(new StringBuffer("登录员工号：").append(dah).append("    机构号：").append(jgh));
        logger.info(String.valueOf(new StringBuffer("登录员工号：")));

         try {
             // 根据档案号获取员工对象
             User user = userService.getUserByDah(dah);
             ModelAndView mv = new ModelAndView();
             // 如果取不到员工对象或者员工对象已经逻辑删除，则提示用户不存在或密码不正确
             // 如果登录界面没输密码或者输入密码不正确，也提示用户不存在或密码不正确
             if (null == user || user.getFlag().equals(String.valueOf(UserStatus.stop.getId()))
             || SystemConstant.BLANK.equals(StringUtils.trimToEmpty(password)) || !user.getPassword().equals(MD5.crypt(StringUtils.trimToEmpty(password))) ) {
                 mv.setViewName(ConstantKey.INDEX);
                 mv.addObject("loginName", dah);
                 mv.addObject("jgh", jgh);
                 mv.addObject(ConstantKey.KEY_MESSAGE, ConstantMessage.INF00001);
                 return mv;
             }

             // 机构-部门的处理
             List<DeptUserVo> depts = userService.getDeptUserList(dah);
             String jgmc = null;
             String jg_no = null;
             if(depts.size()>0)
             {
                 jgmc = depts.get(0).getJgmc();
                 jg_no = depts.get(0).getJgh();
             }
             else
             {
                 // 如果界面没输入机构或者登录员工不属于该机构，则提示机构不存在或机构不正确
                 mv.setViewName(ConstantKey.INDEX);
                 mv.addObject("loginName", dah);
                 mv.addObject("jgh", jgh);
                 mv.addObject(ConstantKey.KEY_MESSAGE, ConstantMessage.INF00002);
                 logger.info("机构号不存在");
                 return mv;
             }

             // session
             String sessionId = httpSession.getId();
             if (LoginListener.getSessionIdMap().get(ConstantKey.KEY_USER) != null) {
                 // 如果账号已经登陆了，则强制注销
                 if (sessionId != null && !sessionId.equals(LoginListener.getSessionIdMap().get(ConstantKey.KEY_USER))) {
                     LoginListener.getSessionIdMap().remove(ConstantKey.KEY_USER);
                 }
             }
             LoginListener.getSessionIdMap().put(ConstantKey.KEY_USER, sessionId);
             // 把认证用户和机构号存入session
             httpSession.setAttribute(ConstantKey.KEY_USER, user);
             httpSession.setAttribute(ConstantKey.KEY_JGH, jg_no);
             httpSession.setAttribute(ConstantKey.KEY_JGMC, jgmc);

             logger.info("USER"+ httpSession.getAttribute("user") + "JGH:" + httpSession.getAttribute("jgh"));
             mv.setViewName(ConstantKey.HOME);
             mv.addObject(ConstantKey.KEY_YGXM, user.getYgxm());
             //logger.info("员工姓名"+user.getYgxm());
             mv.addObject(ConstantKey.KEY_JGMC, jgmc);

             return mv;

         } catch (SQLException e) {
             throw new BusinessException(ConstantMessage.ERR00003, e);
         } catch (NullPointerException e) {
             throw new BusinessException(ConstantMessage.ERR00004, e);
         }
    }

    /**
     * @description: 用户注销
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/10
     **/
    @ResponseBody
    @RequestMapping("logout.do")
    private ModelAndView logout() {
        logger.info("注销");
        // 移除session
        httpSession.removeAttribute(ConstantKey.KEY_USER);
        httpSession.removeAttribute(ConstantKey.KEY_JGH);
        httpSession.invalidate();
        // 跳转到登录页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.INDEX);
        return mv;
    }

    /**
     * @description: 获取树形的菜单，主页左侧的菜单栏的
     *
     * @author: redcomet
     * @param: []
     * @return: void
     * @create: 2018/10/11
     **/
    @ResponseBody
    @RequestMapping(value = "ajax/user_getMenusTree.do")
    private void getMenus() throws BusinessException {
        try {
            // 根据档案号取得相应的菜单
            User user = (User) httpSession.getAttribute(ConstantKey.KEY_USER);
            List<Map<String, Object>> menus = userService.getMenusTree(user.getDah());

            response.getWriter().write(JSON.Encode(menus));

        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:显示用户管理页面
     *
     * @author: redcomet
     * @param: [id]
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/11 
     **/
    @ResponseBody
    @RequestMapping("showUserList.do")
    private ModelAndView showUserList(String id) {
        // 跳转到用户管理页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.USER_LIST);
        mv.addObject(ConstantKey.KEY_MENU_ID, id);
        return mv;
    }

    /**
     * @description:获取用户列表
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void        
     * @create: 2018/10/11 
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getUsers.do")
    private void getUsers(UserSearchFilter filter) throws BusinessException {
        try {
            // 用户查询条件
            filter.setOffset();
            filter.setLimit();
            filter.setLoginDah(getSessionUser().getDah());
            // 取得用户列表
            List<UserVo> userVos = userService.getUsers(filter);
            // 取得用户总件数
            long count = userService.getUsersCount(filter);
            logger.info("用户的总数是："+ count);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, userVos);
            hashmap.put(ConstantKey.KEY_TOTAL, count);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:用户新增/修改页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showUserUpdPop.do")
    private ModelAndView showUserUpdPop() {
        // 跳转到用户更新页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.USER_UPD);
        return mv;
    }



    /**
     * @description:新增用户
     *
     * @author: redcomet
     * @param: [user, jgh]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @LogFace
    @ResponseBody
    @RequestMapping("ajax/user_addUser.do")
    private void addUser(User user, String jgh) throws BusinessException {
        try {
            // 用户状态、密码设定
            user.setFlag(String.valueOf(UserStatus.start.getId()));
            user.setPassword(MD5.crypt(SystemConstant.PWD_DEFAULT));
            // 机构拆分
            String[] arrJgh = jgh.split(SystemConstant.COMMA);
            List<DeptUser> yhjgList = new ArrayList<DeptUser>();
            for (int i = 0; i < arrJgh.length; i++) {
                DeptUser yhjg = new DeptUser();
                yhjg.setDah(user.getDah());
                yhjg.setJgh(arrJgh[i]);
                yhjgList.add(yhjg);
            }
            // 新增用户
            if (userService.getUserByDah(user.getDah()) != null) {
                response.getWriter().write(ConstantKey.FAIL);
            } else {
                userService.createUser(user, yhjgList);
                response.getWriter().write(ConstantKey.SUCCESS);
            }
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:更新员工的信息
     *
     * @author: redcomet
     * @param: [user, jgh, action]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @LogFace
    @ResponseBody
    @RequestMapping("ajax/user_updateUser.do")
    private void updateUser(User user, String jgh, String action) throws BusinessException {
        try {
            // 机构拆分
            // String[] arrJgh = jgh.split(SystemConstant.COMMA);
            List<DeptUser> yhjgList = new ArrayList<DeptUser>();
            // for (int i = 0; i < arrJgh.length; i++) {
            // Yhjg yhjg = new Yhjg();
            // yhjg.setDah(user.getDah());
            // yhjg.setJgh(arrJgh[i]);
            // yhjgList.add(yhjg);
            // }
            // 用户状态设为启用
            user.setFlag(String.valueOf(UserStatus.start.getId()));
            // 更新用户
            userService.updateUser(user, yhjgList);
            // 如果是自己修改信息操作，则更新session
            if (ConstantKey.KEY_EDIT.equals(action)) {
                User userSession = userService.getUserByDah(user.getDah());
                httpSession.setAttribute(ConstantKey.KEY_USER, userSession);
            }
            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    @ResponseBody
    @RequestMapping("ajax/user_initUserList.do")
    private void initUserList(String id) throws BusinessException {
        try {
            // 取得机构列表
            List<InstitutionInfo> jgxx = getJgxxList();
            // 取得员工状态列表
            List<HashMap<String, String>> maps = UserStatus.getUserStatusList();
            // 取得功能权限列表
            List<MenuFunction> menuFunctions = getMenuFunction(id);
            // 查询员工权限
            Boolean searchFlag = false;
            // 创建员工权限
            Boolean addFlag = false;
            // 更新/启用员工权限
            Boolean editFlag = false;
            // 停用员工权限
            Boolean delFlag = false;
            // 密码重置权限
            Boolean pwdFlag = false;
            // 批量添加拼音权限
            Boolean batchPinyinFlag = false;
            // 单独添加拼音权限
            Boolean pinyinFlag = false;
            // 判断登录者是否具有创建员工、更新/启用员工、停用员工、密码重置权限
            for (int i = 0; i < menuFunctions.size(); i++) {
                switch (ActionType.valueOf(menuFunctions.get(i).getFunctionAction())) {
                    case user_getUsers:
                        searchFlag = true;
                        break;
                    case user_addUser:
                        addFlag = true;
                        break;
                    case user_updateUser:
                        editFlag = true;
                        break;
                    case user_deleteUser:
                        delFlag = true;
                        break;
                    case user_resetPwd:
                        pwdFlag = true;
                        break;
                    case user_batchAddPinyin:
                        batchPinyinFlag = true;
                        break;
                    case user_addPinyin:
                        pinyinFlag = true;
                        break;
                    default:
                        break;
                }
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_JGH_LIST, jgxx);
            hashmap.put(ConstantKey.KEY_YGZT, maps);
            hashmap.put(ConstantKey.SEARCH_FLAG, searchFlag);
            hashmap.put(ConstantKey.ADD_FLAG, addFlag);
            hashmap.put(ConstantKey.EDIT_FLAG, editFlag);
            hashmap.put(ConstantKey.DEL_FLAG, delFlag);
            hashmap.put(ConstantKey.PWD_FLAG, pwdFlag);
            hashmap.put("batchPinyinFlag", batchPinyinFlag);
            hashmap.put("pinyinFlag", pinyinFlag);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:获取用户
     *
     * @author: redcomet
     * @param: [dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getUser.do")
    private void getUser(String dah) throws BusinessException {
        try {
            // 取得用户
            User user = userService.getUserByDah(dah);
            List<DeptUserVo> yhjg = userService.getDeptUserList(dah);
            StringBuffer jgh = new StringBuffer();
            String jgxx = SystemConstant.BLANK;
            for (int j = 0; j < yhjg.size(); j++) {
                jgh.append(yhjg.get(j).getJgh()).append(",");
            }
            // 机构
            if (jgh.length() > 0) {
                jgxx = jgh.substring(0, jgh.length() - 1);
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_USER, user);
            hashmap.put(ConstantKey.KEY_JGH_LIST, jgxx);
            hashmap.put(ConstantKey.KEY_JGH, getSessionJgh());
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:修改密码
     *
     * @author: redcomet
     * @param: [dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @LogFace
    @ResponseBody
    @RequestMapping("ajax/user_resetPwd.do")
    public void resetPwd(String dah) throws BusinessException {
        try {
            User user = new User();
            user.setPassword(MD5.crypt(StringUtils.trimToEmpty(SystemConstant.PWD_DEFAULT)));
            user.setDah(dah);
            // 重置用户密码
            userService.resetUserPwd(user);
            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
    
    /**
     * @description:获取岗位列表
     *
     * @author: redcomet
     * @param: []
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getGwList.do")
    private void getGwList() throws BusinessException {
        try {
            // 取得岗位列表
            List<Constant> gwList = getConstantList(ConstantKey.KEY_POSITION);
            String json = JSON.Encode(gwList);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:获取性别列表
     *
     * @author: redcomet
     * @param: []
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getSexList.do")
    private void getSexList() throws BusinessException {
        try {
            // 取得性别列表
            List<HashMap<String, String>> sexList = Sex.getSexList();
            String json = JSON.Encode(sexList);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:停用用户
     *
     * @author: redcomet
     * @param: [dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @LogFace
    @ResponseBody
    @RequestMapping("ajax/user_deleteUser.do")
    private void deleteUser(String dah) throws BusinessException {
        try {
            // 停用用户
            userService.deleteUser(dah);
            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:转入用户更新页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showUserUpd.do")
    private ModelAndView showUserUpd() throws BusinessException {
        try {
            User user = getSessionUser();
            // 跳转到用户更新页面
            ModelAndView mv = new ModelAndView();
            mv.setViewName(ConstantKey.USER_UPD);
            // 设置回显的用户信息、机构号
            mv.addObject(ConstantKey.KEY_USER_INFO, user);
            mv.addObject(ConstantKey.KEY_JGH_LIST, getSessionJgh());
            mv.addObject(ConstantKey.KEY_ACTION, ConstantKey.KEY_EDIT);
            return mv;
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        }
    }

    /**
     * @description:转入修改密码页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView
     * @create: 2018/10/12
     **/
    @ResponseBody
    @RequestMapping("showPwdUpd.do")
    private ModelAndView showPwdUpd() {
        // 密码修改页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.PWD_UPD);
        return mv;
    }

    /**
     * @description:用户修改密码
     *
     * @author: redcomet
     * @param: [oldPwd, newPwd, dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/user_updatePwd.do")
    public void updatePwd(String oldPwd, String newPwd, String dah) throws BusinessException {
        try {
            User user = (User) httpSession.getAttribute(ConstantKey.KEY_USER);
            // 判断密码是否相等
            if (user.getPassword().equals(MD5.crypt(oldPwd))) {
                // 修改用户密码
                User userUpd = new User();
                userUpd.setPassword(MD5.crypt(newPwd));
                userUpd.setDah(user.getDah());
                userService.resetUserPwd(userUpd);
                // 密码更新到session中
                user.setPassword(MD5.crypt(newPwd));
                httpSession.setAttribute(ConstantKey.KEY_USER, user);
                response.getWriter().write(ConstantKey.SUCCESS);
            } else {
                response.getWriter().write(ConstantKey.FAIL);
            }
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }

    }
    
    /**
     * @description:转入机构管理页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showJgList.do")
    private ModelAndView showJgList() {
        // 机构维护页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.INSTITUTION_LIST);
        return mv;
    }

    /**
     * @description:获取机构列表
     *
     * @author: redcomet
     * @param: []
     * @return: void
     * @create: 2018/10/12
     **/
    @ResponseBody
    @RequestMapping("ajax/user_getJgList.do")
    private void getJgList() throws BusinessException {
        try {
            // 取得机构列表
            List<InstitutionInfo> jgxx = getJgxxList();
            List<HashMap<String, Object>> maps = new ArrayList<>();
            HashMap<String, Object> map = new HashMap<>();
            // 把机构列表放到hashmap中
            for (int i = 0; i < jgxx.size(); i++) {
                map = new HashMap<>();
                map.put(ConstantKey.KEY_JGH, jgxx.get(i).getJgh());
                map.put(ConstantKey.KEY_JGMC, jgxx.get(i).getJgmc());
                map.put(ConstantKey.KEY_SJJG, jgxx.get(i).getSjjg());
                map.put(ConstantKey.KEY_SFYDZH, jgxx.get(i).getSfydzh());
                map.put(ConstantKey.KEY_FLAG, jgxx.get(i).getFlag());
                map.put(ConstantKey.KEY_NUM, jgxx.get(i).getNum());
                maps.add(map);
            }
            String json = JSON.Encode(maps);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:检查机构是否重复
     *
     * @author: redcomet
     * @param: [jgh]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/jg_checkJgsfcf.do")
    private void checkJgsfcf(String jgh) throws BusinessException {
        try {
            // 取得机构数目
            int jgCount = userService.getJgCountByJgh(jgh);
            if (jgCount > 0) {
                response.getWriter().write(ConstantKey.FAIL);
            } else {
                response.getWriter().write(ConstantKey.SUCCESS);
            }
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:保存机构数据
     *
     * @author: redcomet
     * @param: [data, removedJSON]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/jg_saveJgData.do")
    private void saveJgData(String data, @RequestParam(value = "removed", required = false) String removedJSON) throws BusinessException {
        try {
            ArrayList jgData = (ArrayList) JSON.Decode(data);
            ArrayList removedData = (ArrayList) JSON.Decode(removedJSON);
            // 树形转换为列表
            ArrayList jgList = TreeUtil.ToList(jgData, ConstantKey.KEY_ROOT_ID, ConstantKey.KEY_CHILDREN, ConstantKey.KEY_JGH, ConstantKey.KEY_SJJG);
            // 生成num
            for (int i = 0, l = jgList.size(); i < l; i++) {
                HashMap node = (HashMap) jgList.get(i);
                node.put(ConstantKey.KEY_NUM, i);
            }

            // 生成pid
            jgList = TreeUtil.ToList(jgData, ConstantKey.KEY_ROOT_ID, ConstantKey.KEY_CHILDREN, ConstantKey.KEY_JGH, ConstantKey.KEY_SJJG);
            userService.updateJg(jgList, removedData);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (JSONException e) {
            throw new BusinessException(ConstantMessage.ERR00006, e);
        }
    }

    /**
     * @description:转入角色管理页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showRoleList.do")
    private ModelAndView showRoleList() {
        // 角色管理页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.ROLE_LIST);
        return mv;
    }


    /**
     * @description:获取角色列表
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_getRoleList.do")
    private void getRoleList(UserSearchFilter filter) throws BusinessException {
        try {
            // 角色查询条件
            filter.setOffset();
            filter.setLimit();
            // 取得角色列表
            List<Role> roles = userService.getRoles(filter);
            // 取得角色总件数
            int count = userService.getRolesCount(filter);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, roles);
            hashmap.put(ConstantKey.KEY_TOTAL, count);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:初始化权限列表
     *
     * @author: redcomet
     * @param: [id]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_initRoleList.do")
    private void initRoleList(String id) throws Exception {
        try {
            // 取得功能权限列表
            List<MenuFunction> menuFunctions = getMenuFunction(id);
            // 删除角色权限
            Boolean delFlag = false;
            // 创建/修改角色和菜单功能权限
            Boolean editFlag = false;
            // 判断登录者是否具有删除角色权限、创建/修改角色和菜单功能权限
            for (int i = 0; i < menuFunctions.size(); i++) {
                switch (ActionType.valueOf(menuFunctions.get(i).getFunctionAction())) {
                    case role_saveRole:
                        editFlag = true;
                        break;
                    case role_deleteRole:
                        delFlag = true;
                        break;
                    default:
                        break;
                }
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.EDIT_FLAG, editFlag);
            hashmap.put(ConstantKey.DEL_FLAG, delFlag);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:转入修改角色页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showRoleUpd.do")
    private ModelAndView showRoleUpd() {
        // 角色修改页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.ROLE_UPD);
        return mv;
    }

    /**
     * @description:修改批量角色页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showPiLiangRoleUpd.do")
    private ModelAndView showPiLiangRoleUpd() {
        // 角色修改页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.PILIANG_ROLE_UPD);
        return mv;
    }


    /**
     * @description:删除角色
     *
     * @author: redcomet
     * @param: [roleId]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_deleteRole.do")
    private void deleteRole(Integer roleId) throws BusinessException {
        try {
            // 检查角色id有没有被分配
            int count = userService.getUserRoleCount(roleId);
            if (count == 0) {
                // 删除角色
                userService.deleteRole(roleId);
                response.getWriter().write(ConstantKey.SUCCESS);
            } else {
                response.getWriter().write(ConstantKey.FAIL);
            }
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:获取菜单功能列表
     *
     * @author: redcomet
     * @param: []
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_getmenuFunctionList.do")
    private void getmenuFunctionList() throws BusinessException {
        try {
            // 取得菜单和菜单功能列表
            List<HashMap<String, Object>> result = userService.getmenuFunctionList();
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, result);
            hashmap.put(ConstantKey.KEY_TOTAL, 1);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:保存角色
     *
     * @author: redcomet
     * @param: [roleVo]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_saveRole.do")
    private void saveRole(RoleVo roleVo) throws BusinessException {
        try {
            String editFlg = roleVo.getEditFlag();
            String menu = roleVo.getMenu();

            if (ConstantKey.ADD_FLAG_VALUE.equals(editFlg)) {
                // 角色表数据
                Role role = roleVo;
                role.setFlag(0);
                role.setCreateTime(new Date());
                role.setUpdateTime(new Date());

                // 更新任务类型基本信息
                userService.createRole(role, menu);
            } else if (ConstantKey.EDIT_FLAG_VALUE.equals(editFlg)) {
                // 角色表数据
                Role role = roleVo;
                role.setUpdateTime(new Date());

                // 更新任务类型基本信息
                userService.updateRole(role, menu);
            }

            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:获取角色菜单
     *
     * @author: redcomet
     * @param: [roleId]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_getRoleMenu.do")
    private void getRoleMenu(Integer roleId) throws BusinessException {
        try {
            // 取得角色的菜单和菜单功能
            String result = userService.getRoleMenu(roleId);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, result);
            hashmap.put(ConstantKey.KEY_TOTAL, 1);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:转入员工角色管理页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showUserRoleList.do")
    private ModelAndView showUserRoleList() {
        // 用户角色管理页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.USER_ROLE_LIST);
        return mv;
    }

    /**
     * @description:根据登录的用户获取机构列表
     *
     * @author: redcomet
     * @param: []
     * @return: void        
     * @create: 2018/10/12 
     **/
    @RequestMapping("ajax/cg_getJgList.do")
    @ResponseBody
    public void cg_getJgList() {
        try {
            String dah = getSessionUser().getDah();
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

    /**
     * @description:获取用户的角色
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void
     * @create: 2018/10/12
     **/
    @ResponseBody
    @RequestMapping("ajax/role_getUserRoles.do")
    private void getUserRoles(UserSearchFilter filter) throws BusinessException {
        try {
            // 员工角色查询分页条件
            filter.setOffset();
            filter.setLimit();
            filter.setLoginDah(getSessionUser().getDah());
            String jgh = filter.getJgh();
            // 获取子机构
            if (!StringUtil.isEmpty(jgh)) {
                List<InstitutionInfo> jgxx = null;
                StringBuffer sb = new StringBuffer(jgh);
                // 如果是总行，则获取全部
                /*
                if ("070667999".equals(jgh)) {
                    jgxx = userService.getInstitutionInfosNew();
                } else {
                    jgxx = userService.getChildJgByJgh(jgh);
                }*/
                jgxx = userService.getChildJgByJgh(jgh);
                int size = 0;
                if (jgxx != null && (size = jgxx.size()) > 0) {
                    sb.append(SystemConstant.COMMA);
                    for (int i = 0; i < size; i++) {
                        InstitutionInfo info = jgxx.get(i);
                        sb.append(info.getJgh());
                        if (i != size - 1) {
                            sb.append(SystemConstant.COMMA);
                        }
                    }
                }
                filter.setJgh(sb.toString());
            }
            // 取得员工角色列表
            List<UserRoleVo> roles = userService.getUserRoles(filter);
            // 取得员工角色总件数
            int count = userService.getUserRolesCount(filter);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, roles);
            hashmap.put(ConstantKey.KEY_TOTAL, count);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:初始化用户角色列表
     *
     * @author: redcomet
     * @param: [id]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_initUserRoleList.do")
    private void initUserRoleList(String id) throws BusinessException {
        try {
            // 取得功能权限列表
            List<MenuFunction> menuFunctions = getMenuFunction(id);
            // 员工角色分配权限
            Boolean roleSetFlag = false;
            // 数据权限分配权限
            Boolean dataSetFlag = false;
            // 判断登录者是否具有员工角色分配、数据权限分配权限
            for (int i = 0; i < menuFunctions.size(); i++) {
                switch (ActionType.valueOf(menuFunctions.get(i).getFunctionAction())) {
                    case role_saveUserRole:
                        roleSetFlag = true;
                        break;
                    case role_saveDataAuthority:
                        dataSetFlag = true;
                        break;
                    default:
                        break;
                }
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.ROLE_SET_FLAG, roleSetFlag);
            hashmap.put(ConstantKey.DATA_SET_FLAG, dataSetFlag);
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:显示角色分配页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("showRoleSet.do")
    private ModelAndView showRoleSet() {
        // 角色分配页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.ROLE_SET);
        return mv;
    }

   /**
    * @description:显示分配数据权限页面
    *
    * @author: redcomet
    * @param: []
    * @return: org.springframework.web.servlet.ModelAndView        
    * @create: 2018/10/12 
    **/
    @ResponseBody
    @RequestMapping("showDataAuthoritySet.do")
    private ModelAndView showDataAuthoritySet() {
        // 数据权限分配页面
        ModelAndView mv = new ModelAndView();
        mv.setViewName(ConstantKey.DATA_AUTHORITY_SET);
        return mv;
    }

    /**
     * @description:初始化数据分配权限页面
     *
     * @author: redcomet
     * @param: [dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_initDataAuthoritySet.do")
    private void initDataAuthoritySet(String dah) throws BusinessException {
        try {
            // 取得机构列表
            List<InstitutionInfo> jgxx = getJgxxList();
            List<HashMap<String, Object>> jgMaps = new ArrayList<>();
            // 把机构列表放到hashmap中
            for (int i = 0; i < jgxx.size(); i++) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put(ConstantKey.KEY_JGH, jgxx.get(i).getJgh());
                temp.put(ConstantKey.KEY_JGMC, jgxx.get(i).getJgmc());
                temp.put(ConstantKey.KEY_SJJG, jgxx.get(i).getSjjg());
                jgMaps.add(temp);
            }

            // 取得已有的数据权限
            String dataAuthority = userService.getDataAuthority(dah);
            HashMap<String, Object> hashmap = new HashMap<String, Object>();

            hashmap.put(ConstantKey.KEY_DATA, jgMaps);
            hashmap.put(ConstantKey.KEY_DATA_AUTHORITY, dataAuthority);
            hashmap.put(ConstantKey.KEY_TOTAL, jgxx.size());
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }

    }

    /**
     * @description:保存数据权限
     *
     * @author: redcomet
     * @param: [dah, jg]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_saveDataAuthority.do")
    private void saveDataAuthority(String dah, String jg) throws BusinessException {
        try {
            List<DataAuthority> dataAuthoritys = new ArrayList<DataAuthority>();
            DataAuthority dataAuthority = new DataAuthority();
            // 给数据权限表表的entity赋值
            String[] jgs = jg.split(SystemConstant.COMMA);
            for (int i = 0; i < jgs.length; i++) {
                dataAuthority = new DataAuthority();
                dataAuthority.setDah(dah);
                dataAuthority.setJgh(jgs[i]);
                dataAuthority.setCreateTime(new Date());
                dataAuthoritys.add(dataAuthority);
            }

            // 创建数据权限
            userService.createDataAuthority(dataAuthoritys);
            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:初始化角色分配页面
     *
     * @author: redcomet
     * @param: [dah]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_initRoleSet.do")
    private void initRoleSet(String dah) throws BusinessException {
        try {
            // 取得所有角色列表
            UserSearchFilter filter = new UserSearchFilter();
            List<Role> roles = userService.getRoles(filter);

            // 根据档案号取得用户角色
            List<Role> userRoleVos = userService.getRolesByDah(dah);
            Boolean flag = true;
            List<HashMap<String, Object>> data = new ArrayList<>();
            // 把用户已经分配的角色从所有角色列表中去除
            for (int i = 0; i < roles.size(); i++) {
                flag = true;
                // flag是false时表示该角色已经分配给用户
                for (int j = 0; j < userRoleVos.size(); j++) {
                    if (roles.get(i).getId() == userRoleVos.get(j).getId()) {
                        flag = false;
                        break;
                    }
                }
                // flag是true时，把未分配给该用户的角色增加到新的数组中
                if (flag) {
                    HashMap<String, Object> temp = new HashMap<>();
                    temp.put(ConstantKey.KEY_ROLE_ID, roles.get(i).getId());
                    temp.put(ConstantKey.KEY_ROLE_NAME, roles.get(i).getRoleName());
                    data.add(temp);
                }
            }
            List<HashMap<String, Object>> userRoles = new ArrayList<>();
            // 把用户角色列表放到hashmap中
            for (int k = 0; k < userRoleVos.size(); k++) {
                HashMap<String, Object> temp = new HashMap<>();
                temp.put(ConstantKey.KEY_ROLE_ID, userRoleVos.get(k).getId());
                temp.put(ConstantKey.KEY_ROLE_NAME, userRoleVos.get(k).getRoleName());
                userRoles.add(temp);
            }
            HashMap<String, Object> hashmap = new HashMap<String, Object>();
            hashmap.put(ConstantKey.KEY_DATA, data);
            hashmap.put(ConstantKey.KEY_USER_ROLES, userRoles);
            hashmap.put(ConstantKey.KEY_TOTAL, roles.size());
            String json = JSON.Encode(hashmap);
            response.getWriter().write(json);
        } catch (SQLException e) {
            throw new BusinessException(ConstantMessage.ERR00003, e);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }

    /**
     * @description:保存用户角色
     *
     * @author: redcomet
     * @param: [dah, jsonRole]
     * @return: void        
     * @create: 2018/10/12 
     **/
    @ResponseBody
    @RequestMapping("ajax/role_saveUserRole.do")
    private void saveUserRole(String dah, String jsonRole) throws BusinessException {
        try {
            ArrayList arrRole = (ArrayList) JSON.Decode(jsonRole);
            List<UserRole> userRoles = new ArrayList<UserRole>();
            UserRole userRole = new UserRole();

            for (int i = 0; i < arrRole.size(); i++) {
                userRole = new UserRole();
                Map objRole = (Map) arrRole.get(i);
                userRole.setDah(dah);
                userRole.setRoleId(Integer.valueOf(objRole.get(ConstantKey.KEY_ROLE_ID).toString()));
                userRoles.add(userRole);
            }

            // 创建用户角色
            userService.createUserRole(userRoles);
            response.getWriter().write(ConstantKey.SUCCESS);
        } catch (NullPointerException e) {
            throw new BusinessException(ConstantMessage.ERR00004, e);
        } catch (IOException e) {
            throw new BusinessException(ConstantMessage.ERR00005, e);
        }
    }
}
