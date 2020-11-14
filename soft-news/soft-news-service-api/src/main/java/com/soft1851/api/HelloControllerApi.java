package com.soft1851.api;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName HelloControllerApi接口
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/13
 **/
public interface HelloControllerApi {
    /**
     * hello接口
     * @return object
     */
    @GetMapping("/hello")
    Object hello();
}
