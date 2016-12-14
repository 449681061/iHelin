package com.seven.ihelin.controller.admin;

import com.seven.ihelin.config.CommonConfig;
import com.seven.ihelin.controller.BaseController;
import com.seven.ihelin.model.AdminUser;
import com.seven.ihelin.util.RequestUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("admin")
public class AdminLoginController extends BaseController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(Model model, String from,HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("test")) {
                // 如果user Cookie存在，进行处理
                break;
            }
        }
        model.addAttribute("from", from);
        return "admin/login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String username, String password, String from, HttpServletRequest request,
                        HttpSession session, Model model, HttpServletResponse response) {
        if (username.equals(CommonConfig.getAdminUser()) && password.equals(CommonConfig.getAdminPassword())) {
            Cookie cookie = new Cookie("user", UUID.randomUUID().toString());
            cookie.setMaxAge(365 * 24 * 3600);
            cookie.setPath("/");
            response.addCookie(cookie);
            AdminUser adminUser = new AdminUser();
            adminUser.setAdminId(username);
            adminUser.setNickName("超级管理员");
            adminUser.setLastLoginTime(new Date());
            String rip = RequestUtil.getRealIp(request);
            adminUser.setLastLoginIp(rip);
            session.setAttribute(SESSION_KEY_ADMIN, adminUser);
            LOGGER.info("Admin user: {} login success,ip : {}.", username, rip);
            if (StringUtils.isNotEmpty(from))
                return "redirect:" + from;
            return "redirect:/admin/index";
        } else {
            model.addAttribute("error", "用户名密码不正确！");
            model.addAttribute("from", from);
            return adminFtl("login");
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute(SESSION_KEY_ADMIN);
        return "redirect:login";
    }

}
