package me.ianhe.utils;

import java.util.Arrays;

/**
 * 微信签名验证
 * Created by Ian He on 16/11/4.
 */
public class CheckUtil {

    private static final String TOKEN = Global.getWXToken();

    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        String[] arr = new String[]{TOKEN, timestamp, nonce};
        // 排序
        Arrays.sort(arr);
        // Collections.sort(Arrays.asList(arr));
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        // sha1加密
        String temp = CryptUtil.sha1(content.toString());
        return temp.equals(signature);
    }

    private CheckUtil() {
        //工具类不允许实例化
    }

}
