package me.ianhe.controller;

import me.ianhe.utils.CryptUtil;
import me.ianhe.utils.JSON;
import me.ianhe.utils.MailUtil;
import me.ianhe.utils.TemplateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;

@Controller
public class TestController extends BaseController {

    @ResponseBody
    @RequestMapping(value = "mail", method = RequestMethod.GET)
    public String mail() {
        String template = TemplateUtil.applyTemplate("/mail/mail_content.ftl");
        MailUtil.sendMail("ianhe@outlook.com", "iHelin", "哈哈", template);
        return template;
    }

    @RequestMapping(value = "config", method = RequestMethod.GET)
    public String configPage(Model model) {
        Properties props = System.getProperties();
        model.addAttribute("props", props);
        return "config";
    }

    @ResponseBody
    @RequestMapping(value = "console", method = RequestMethod.GET)
    public String getProperties() {
        Properties props = System.getProperties();
        return JSON.toJson(props);
    }

    @ResponseBody
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test() {
        return "<h1>三个人请问abc123</h1>";
    }

    @RequestMapping(value = "test1", method = RequestMethod.GET)
    public void test1(HttpServletResponse response) throws IOException {
        response.getWriter().print("<h1>三个人请问abc123</h1>");
    }

    public static void main(String[] args) {
        String s = "123456";
        System.out.println(CryptUtil.md5(s));
        System.out.println(DigestUtils.md5DigestAsHex(s.getBytes()));
    }

}