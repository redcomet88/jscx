package jssvc.user.controller;

import flexjson.JSONException;
import jssvc.base.constant.ConstantKey;
import jssvc.base.constant.ConstantMessage;
import jssvc.base.constant.SystemConstant;
import jssvc.base.controller.BaseController;
import jssvc.base.exception.BusinessException;
import jssvc.base.listener.LoginListener;
import jssvc.base.enums.Sex;
import jssvc.base.model.Constant;
import jssvc.base.util.JSON;
import jssvc.base.util.MD5;
import jssvc.base.util.TreeUtil;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
             // TODO
             List<DeptUserVo> depts = userService.getDeptUserList(dah);
             String jgmc = null;
             if(depts.size()>0)
             {
                 jgmc = depts.get(0).getJgmc();
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
}
