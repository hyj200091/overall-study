package com.soft1851.api;

import com.soft1851.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BaseController
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/15
 **/
public class BaseController {
    // 这里要声明为public 要不子类不能使用
    @Autowired
    public RedisOperator redis;
    public static  final  String MOBILE_SMSCODE = "mobile:smscode";
    public static final String REDIS_USER_TOKEN = "redis_user_token";
    public static final String REDIS_USER_INFO = "redis_user_info";

    public static final Integer COOKIE_MONTH = 30 * 24 * 60 * 60;

    @Value("${website.domain-name}")
    public String DOMAIN_NAME;

    /**
     * 获取BO中的错误信息，可以通过统一的异常处理返回给客户端
     *
     * @param result 入参
     */
    public Map<String,String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<>(16);
        List<FieldError> errorList = result.getFieldErrors();
        for (FieldError error: errorList) {
            // 发送验证码错误的时候对应的某个属性
            String field = error.getField();
            // 验证的错误消息
            String msg = error.getDefaultMessage();
            map.put(field,msg);
        }
        return map;
    }

    /**
     *
     * @param request 请求
     * @param response 响应
     * @param cookieName cookie名称
     * @param cookieValue cookie值
     * @param maxAge 最大
     */
    public void  setCookie(HttpServletRequest request, HttpServletResponse response,
                           String cookieName,
                           String cookieValue,
                           Integer maxAge) {
        try {
            cookieValue = URLEncoder.encode(cookieValue,"utf-8");
            // 这个地方老班打的是setcookieValue 但是貌似是没有被定义的把
            setCookieValue(request,response,cookieName,cookieValue,maxAge);
            Cookie cookie = new Cookie(cookieName,cookieValue);
            cookie.setMaxAge(maxAge);
            cookie.setDomain(DOMAIN_NAME);
            cookie.setPath("/");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void  setCookieValue(HttpServletRequest request,HttpServletResponse response,
                                String cookieName,
                                String cookieValue,
                                Integer maxAge) {
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setMaxAge(maxAge);
        cookie.setDomain(DOMAIN_NAME);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}