package jssvc.credit.controller;

import jssvc.base.controller.BaseController;
import jssvc.credit.service.CreditReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class CreditReportController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CreditReportController.class);

    @Autowired
    private CreditReportService creditReportService;

    @RequestMapping("showCreditReport.do")
    public ModelAndView showCreditRport() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("credit/creditReport");
        return mv;
    }

    @RequestMapping("showCreditDashboard.do")
    public String showCreditDashboard(Model model) {
        HashMap<String, Object> resultMap = creditReportService.getTotalCreditCaseSummary();
        model.addAttribute("totalSummary", resultMap);

        return "credit/creditDashboard";
    }
}
