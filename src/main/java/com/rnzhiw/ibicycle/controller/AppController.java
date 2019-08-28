package com.rnzhiw.ibicycle.controller;

import com.rnzhiw.ibicycle.common.Constants;
import com.rnzhiw.ibicycle.model.form.SessionInfo;
import com.rnzhiw.ibicycle.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 入口控制器
 *
 * @author fuenhui
 * @date 2018/11/24
 */
@Controller
public class AppController {

    private static Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    MemberService memberService;

    /**
     * 登录页面
     *
     * @param session
     * @return
     */
    @GetMapping("/login")
    public String login(HttpSession session) {

        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.SESSION_INFO);

//        System.out.println(sessionInfo); //null

        if (sessionInfo != null) {
            return "redirect:/";
        }
        return "login";
    }


    /**
     * 执行登录
     *
     * @param mobile 手机号
     * @param password 密码
     * @param attributes
     * @param session 会话
     * @return
     */
    @PostMapping("/login")
    public String doLogin(String mobile, String password, RedirectAttributes attributes, HttpSession session) {

        //设置错误跳转页面
        String errReturn = "redirect:/login";

        //执行登录
        //若成功则返回"success"
        //若失败则返回失败具体原因，并带回给前端显示
        String loginResult = null;
        try {
            loginResult = memberService.login(mobile, password, session);
            logger.info(loginResult);
            logger.info(mobile + password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ("success".equals(loginResult)) {
            System.out.println(111);
            return "redirect:/";
        } else {
            attributes.addFlashAttribute("error", loginResult);
            return errReturn;
        }
    }

    /**
     * 登出
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login";
    }


    @RequestMapping("/")
    public String index(HttpSession session, Model model) {

        logger.info("已跳转");

        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(Constants.SESSION_INFO);
        System.out.println(sessionInfo); //null
        if(sessionInfo == null) {
            return "/login";
        } else {
            model.addAttribute("menus",sessionInfo.getMenus());
            model.addAttribute("user", memberService.findOne(sessionInfo.getUserId()));
            return "/index";
        }

    }
}