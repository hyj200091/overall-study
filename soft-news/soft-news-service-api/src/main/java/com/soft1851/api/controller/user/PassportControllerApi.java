package com.soft1851.api.controller.user;

import com.soft1851.pojo.bo.RegistLoginBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @ClassName 通行证Api
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/15
 **/
@Api(value = "用户注册登录信息",tags = "用户注册登录Controller")
@RequestMapping("passport")
public interface PassportControllerApi {

    /**
     * 发送短信
     * @param mobile 手机号
     * @param request 请求
     * @return 统一响应
     */
    @ApiOperation(value = "获取短信验证码",notes = "获得短信验证码",httpMethod = "GET")
    @GetMapping("/smsCode")
    GraceResult getCode(@RequestParam String mobile, HttpServletRequest request);

    /**
     * 一键注册登录接口
     * @param registLoginBO 注册登录入参
     * @param result 校验结果
     * @param request  请求
     * @param response 响应
     * @return  GraceResult
     */
    @ApiOperation(value = "一键注册登录接口", notes = "一键注册登录接口",httpMethod = "POST")
    @PostMapping("/sign")
    GraceResult doSign(@RequestBody @Valid RegistLoginBO registLoginBO,
                       BindingResult result,
                       HttpServletRequest request,
                       HttpServletResponse response);
}
