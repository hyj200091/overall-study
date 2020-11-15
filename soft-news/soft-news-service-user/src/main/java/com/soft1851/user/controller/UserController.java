package com.soft1851.user.controller;

import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.result.GraceResult;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.utils.IpUtil;
import com.soft1851.utils.RedisOperator;
import com.soft1851.utils.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static com.soft1851.api.BaseController.MOBILE_SMSCODE;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/14
 **/
@RestController
public class UserController implements UserControllerApi {
    @Autowired
    private RedisOperator redis;
    @Resource
    private AppUserMapper appUserMapper;
    @Resource
    private SmsUtil smsUtil;
    @Override
    public GraceResult getAllUsers() {
        return GraceResult.ok(appUserMapper.selectAll());
    }


    @Override
    public GraceResult getUserInfo(String userId) {
        Example userExample = new Example(AppUser.class);
        Example.Criteria criteria = userExample.createCriteria();
        criteria.andEqualTo("id",userId);
        AppUser user = appUserMapper.selectOneByExample(userExample);
        return GraceResult.ok(user);
    }

    @Override
    public GraceResult getCode(String mobile, HttpServletRequest request) {
        // 生成随机验证码并且发送短信
//        String random = (int) ((Math.random() * 9 + 1) * 10000) + "";
//        System.out.println(random);
//        smsUtil.sendSms(mobile,random);
        // 获得用户id
        String userIp = IpUtil.getRequestIp(request);
        // 根据用户的ip进行限制，限制用户在60秒只能获得一次验证码
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp,userIp);
        // 生成随机验证码并且发送验证码
         String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
         smsUtil.sendSms(mobile,random);
         // 把验证码存入redis，用户后续进行验证
        redis.set(MOBILE_SMSCODE + ":" + mobile,random, 30 * 60);
        return GraceResult.ok();
    }
}