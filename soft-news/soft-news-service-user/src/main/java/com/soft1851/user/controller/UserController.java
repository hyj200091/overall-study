package com.soft1851.user.controller;

import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.result.GraceResult;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.utils.SmsUtil;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/14
 **/
@RestController
public class UserController implements UserControllerApi {
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
        String random = (int) ((Math.random() * 9 + 1) * 10000) + "";
        System.out.println(random);
        smsUtil.sendSms(mobile,random);
        return GraceResult.ok();
    }
}