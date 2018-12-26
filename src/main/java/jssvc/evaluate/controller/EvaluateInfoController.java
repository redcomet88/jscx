package jssvc.evaluate.controller;

import jssvc.base.constant.ConstantMessage;
import jssvc.base.controller.BaseController;
import jssvc.base.enums.SortOrder;
import jssvc.base.exception.BusinessException;
import jssvc.base.service.BaseService;
import jssvc.base.util.JSON;
import jssvc.evaluate.model.EvaluateRecord;
import jssvc.evaluate.service.EvaluateInfoService;
import jssvc.evaluate.vo.EvaluateRecordVo;
import jssvc.evaluate.vo.filter.EvaluateRecordSearchFilter;
import jssvc.user.model.User;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 干部评测Controller
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
    @Autowired
    private EvaluateInfoService evaluateService;

    private String currentUser;


    /**
     * @description:转入干部测评页面
     *
     * @author: redcomet
     * @param: []
     * @return: org.springframework.web.servlet.ModelAndView        
     * @create: 2018/12/17
     **/
    @RequestMapping("showEnluateInfo.do")
    public ModelAndView showEnluateInfo() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("evaluate/evaluteInfo");
        return mv;
    }

    /**
     * @description:评测数据列表查询
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void
     * @create: 2018/10/22
     **/
    @ResponseBody
    @RequestMapping("ajax/evaluate_evaluateRecordList.do")
    private void evaluateRecordList(EvaluateRecordSearchFilter filter) throws BusinessException {
        try {
            User user = getSessionUser();
            filter.setOffset();
            filter.setLimit();
            filter.setDah(user.getDah());
            filter.setSortField("id");
            filter.setSortOrder(SortOrder.ASC.toString());
            List<EvaluateRecordVo> list = evaluateService.getEvaluateRecordList(filter);
            int count = evaluateService.getEvaluateRecordListCount(filter);
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
     * @description:评测数据列表查询
     *
     * @author: redcomet
     * @param: [filter]
     * @return: void
     * @create: 2018/10/22
     **/
    @ResponseBody
    @RequestMapping("ajax/evaluate_evaluateRecordListForAllChosen.do")
    private void evaluateRecordListForAllChosen(EvaluateRecordSearchFilter filter) throws BusinessException {
        try {
            User user = getSessionUser();
            filter.setOffset();
            filter.setLimit();
            filter.setDah(user.getDah());
            filter.setSortField("id");
            filter.setSortOrder(SortOrder.ASC.toString());
            List<EvaluateRecordVo> list = evaluateService.getEvaluateRecordListForAllChosen(filter);
            int count = evaluateService.getEvaluateRecordListCount(filter);
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
     * 述职报告预览
     * @param path
     * @return
     */
    @RequestMapping("previewJobReport.do")
    public ModelAndView previewJobReport(String path) {
        ModelAndView mv = new ModelAndView();
        String rootPath = request.getSession().getServletContext().getRealPath(path);

        path = path.substring(0, path.lastIndexOf(".")) + ".swf";

        System.out.println("path:" + path);
        mv.addObject("path", path);
        mv.setViewName("base/previewAttach");
        return mv;
    }
}
