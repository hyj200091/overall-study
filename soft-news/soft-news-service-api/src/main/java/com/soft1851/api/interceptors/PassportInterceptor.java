package com.soft1851.api.interceptors;

import com.soft1851.exception.GraceException;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.RedisOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName PassportInterceptor
 * @Description 通行证接口拦截
 * @Author hyj
 * @Date 2020/11/16
 **/
public class PassportInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisOperator redis;

    public static final String MOBILE_SMSCODE  = "mobile:smscode";

    /**
     * 拦截请求： 访问controller之前
     * @param  request 请求
     * @param  response 响应
     * @param  handle  拦截
     * @return boolean
     * @throws Exception 抛出异常
     */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handle) throws Exception {
        // 获得用户ip
        String userIp = IpUtil.getRequestIp(request);
        // 检查redis中是否存在这个ip
        boolean keyIsExist = redis.keyIsExist(MOBILE_SMSCODE + ":" + userIp);
        if (keyIsExist) {
            GraceException.display(ResponseStatusEnum.SMS_NEED_WAIT_ERROR);
            // System.out.println("短信发送频率太大！");
            // 请求拦截
            return false;
        }
        // true 请求通过验证 放行
        return true;
    }

    /**
     * 请求访问到controller之后，渲染视图之前
     *
     * @param request  请求
     * @param response 响应
     * @param  handle 拦截
     * @param modelAndView 视图
     * @throws Exception 抛出异常
     */

    @Override
    public  void  postHandle(HttpServletRequest request, HttpServletResponse response, Object handle, ModelAndView modelAndView) throws Exception {

    }

    /**
     *  请求访问controller之后，渲染图层之前
     *
     * @param  request 请求
     * @param  response 响应
     * @param  handle 拦截
     * @param  modelAndView 视图
     * @throws Exception 抛出异常
     */

    @Override
    public  void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handle,Exception ex) throws Exception{

    }
}