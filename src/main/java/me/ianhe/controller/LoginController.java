package me.ianhe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController extends BaseController {

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String doLogin() {
        return "login";
    }

}
