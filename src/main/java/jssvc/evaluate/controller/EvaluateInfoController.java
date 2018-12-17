package jssvc.evaluate.controller;

import jssvc.base.controller.BaseController;
import jssvc.base.service.BaseService;
import jssvc.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
}
