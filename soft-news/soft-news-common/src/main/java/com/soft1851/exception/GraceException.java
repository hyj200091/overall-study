package com.soft1851.exception;

import com.soft1851.result.ResponseStatusEnum;

/**
 *
 * @ClassName GraceException
 * @description: 优雅的统一处理异常
 * @Author hyj
 * @Date 2020/11/16
 **/
public class GraceException {
    public static void display(ResponseStatusEnum responseStatusEnum) {
        throw new MyCustomException(responseStatusEnum);
    }
}