package com.soft1851.api.controller.user;

import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @ClassName UserControllerApi
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/14
 **/
@Api(value = "用户信息相关Controller",tags = "{用户信息相关Controller}")
@RequestMapping("user")
public interface UserControllerApi {
    /**
     * 获取所有用户
     * @return users 所有用户信息a
     */
    @ApiOperation(value = "获得所有用户信息",notes = "获得所有用户信息",httpMethod = "POST")
    @PostMapping("/all")
    GraceResult getAllUsers();

    /**
     * 获得用户基本信息
     * @param userId
     * @return 用户基本信息
     */
    @ApiOperation(value = "获得用户基本信息",notes = "获得用户基本信息",httpMethod = "POST")
    @PostMapping("/userInfo")
    GraceResult getUserInfo(@RequestParam String userId);
}
