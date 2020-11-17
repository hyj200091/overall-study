package com.soft1851.user.controller;

import com.soft1851.api.controller.user.UserControllerApi;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.vo.UserAccountInfoVo;
import com.soft1851.result.GraceResult;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/14
 **/
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController implements UserControllerApi {

    private final AppUserMapper appUserMapper;
    private final UserService userService;
    @Override
    public GraceResult getAllUsers() {
        return GraceResult.ok(appUserMapper.selectAll());
    }


    @Override
    public GraceResult getUserInfo(String userId) {
//        Example userExample = new Example(AppUser.class);
//        Example.Criteria criteria = userExample.createCriteria();
//        criteria.andEqualTo("id",userId);
//        AppUser user = appUserMapper.selectOneByExample(userExample);
//        return GraceResult.ok(user);

        // 判断不能为空
        if (StringUtils.isBlank(userId)) {
            return GraceResult.errorCustom(ResponseStatusEnum.UN_LOGIN);
        }
        // 1 根据userId查询用户，调用内部封装方法(复用，扩展方便)
        AppUser user = getUser(userId);
        // 2 设置VO--需要展示的信息
        UserAccountInfoVo accountVo = new UserAccountInfoVo();
        // 3 拷贝属性
        BeanUtils.copyProperties(user,accountVo);
        return GraceResult.ok(accountVo);
    }
    private AppUser getUser(String userId) {
        // 本方法后续带扩展
        return userService.getUser(userId);
    }
}