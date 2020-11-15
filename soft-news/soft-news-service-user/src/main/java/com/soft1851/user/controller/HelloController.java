package com.soft1851.user.controller;

import com.soft1851.api.HelloControllerApi;
import com.soft1851.result.GraceResult;
import com.soft1851.utils.RedisOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController1
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/13
 **/
@RestController
@Slf4j
public class HelloController implements HelloControllerApi {
    @Autowired
    private RedisOperator redisOperator;
    @Override
    public Object hello() {
        log.info("info: hello");
        return GraceResult.ok("hello");
    }

    @GetMapping("/redis")
    public GraceResult redis() {
        redisOperator.set("age","20");
        return GraceResult.ok(redisOperator.get("age"));
    }
}