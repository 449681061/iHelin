package me.ianhe.utils;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ResponseUtil {

    public static void writeHtml(HttpServletResponse response, String content) {
        if (response == null)
            return;
        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeSuccessJSON(HttpServletResponse response) {
        writeSuccessJSON(response, null);
    }

    public static void writeSuccessJSON(HttpServletResponse response, Map<String, Object> res) {
        if (response == null)
            return;
        if (res == null)
            res = Maps.newHashMap();
        res.put("status", "success");
        response.setContentType("text/json; charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJson(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeFailedJSON(HttpServletResponse response, String error) {
        writeFailedJSON(response, error, null);
    }

    public static void writeFailedJSON(HttpServletResponse response, String error, Map<String, Object> res) {
        if (response == null)
            return;
        if (res == null)
            res = Maps.newHashMap();
        res.put("status", "failed");
        res.put("error", error);
        response.setContentType("text/json; charset=UTF-8");
        try {
            response.getWriter().write(JSON.toJson(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeJSONP(HttpServletResponse response, String callback, String jsData) {
        if (response == null)
            return;
        response.setContentType("text/plain; charset=UTF-8");
        try {
            response.getWriter().write(callback + "(" + jsData + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ResponseUtil() {
        //工具类不允许实例化
    }
}
