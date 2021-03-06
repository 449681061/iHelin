package me.ianhe.controller;

import me.ianhe.db.entity.Advice;
import me.ianhe.utils.ResponseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class AdviceController extends BaseController {

    @PostMapping(value = "advice")
    public void advice(String name, String email, String message, HttpServletResponse response) {
        Advice advice = new Advice();
        advice.setName(name);
        advice.setEmail(email);
        advice.setMessage(message);
        advice.setCreateTime(new Date());
        adviceManager.insertMessage(advice);
        ResponseUtil.writeSuccessJSON(response);
    }
}
