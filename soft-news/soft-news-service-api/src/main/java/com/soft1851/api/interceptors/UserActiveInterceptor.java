package com.soft1851.api.interceptors;

import com.soft1851.exception.GraceException;
import com.soft1851.pojo.AppUser;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.utils.JsonUtil;
import com.soft1851.utils.RedisOperator;
import com.soft1851.utils.UserStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName UserActiveInterceptor
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/17
 **/
public class UserActiveInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisOperator redis;

    public static final String REDIS_USER_INFO = "redis_user_info";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String userId = request.getHeader("headerUserId");
        // 1 可以userController中拷贝过来
        String userJson = redis.get(REDIS_USER_INFO + ":" + userId);
        AppUser user;
        if (StringUtils.isNotBlank(userJson)) {
            user = JsonUtil.jsonToPojo(userJson,AppUser.class);
        }else  {
            GraceException.display(ResponseStatusEnum.UN_LOGIN);
            return false;
        }

        // 如果不是激活状态不能执行后续操作
        assert  user  != null;
        if (user.getActiveStatus() == null || user.getActiveStatus() != UserStatus.ACTIVE.type) {
            GraceException.display(ResponseStatusEnum.USER_INACTIVE_ERROR);
            return false;
        }
        return true;
    }
}