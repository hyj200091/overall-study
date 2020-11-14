package com.soft1851.api.controller.user;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName UserControllerApi
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/14
 **/
public interface UserControllerApi {
    /**
     * 获取所有用户
     * @return users 所有用户信息a
     */
    @GetMapping("/users")
    Object getUsers();
}
