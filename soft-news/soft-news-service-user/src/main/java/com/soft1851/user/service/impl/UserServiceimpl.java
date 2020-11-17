package com.soft1851.user.service.impl;

import com.soft1851.exception.GraceException;
import com.soft1851.org.n3r.idworker.Sid;
import com.soft1851.pojo.AppUser;
import com.soft1851.pojo.bo.UpdateUserInfoBO;
import com.soft1851.result.ResponseStatusEnum;
import com.soft1851.user.mapper.AppUserMapper;
import com.soft1851.user.service.UserService;
import com.soft1851.utils.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @ClassName UserServiceimpl
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/16
 **/
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceimpl implements UserService {
    public final AppUserMapper appUserMapper;
    private final RedisOperator redis;

    @Resource
    private Sid sid;

    public static final String REDIS_USER_INFO = "redis_user_info";

    public  static  final  String USER_FACE0 = "https://niit-soft.oss-cn-hangzhou.aliyuncs.com/avatar/8.jpg";
    @Override
    public AppUser queryMobileIsExist(String mobile) {
        Example userExample = new Example(AppUser.class);
        Example.Criteria userCriteria = userExample.createCriteria();
        userCriteria.andEqualTo("mobile",mobile);
        return appUserMapper.selectOneByExample(userExample);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AppUser createUser(String mobile) {
        // 若分库分表 数据库主键id必须保证全局（全库） 唯一 ，不得重复
        String userId = sid.nextShort();
        // 构建用户对象
        AppUser user = AppUser.builder()
                .id(userId)
                .mobile(mobile)
                .nickname("用户:" + DesensitizationUtil.commonDisplay(mobile))
                .face(USER_FACE0)
                .birthday(DateUtil.stringToDate("2000-01-02"))
                .activeStatus(UserStatus.INACTIVE.type)
                .totalIncome(0)
                .createdTime(new Date())
                .updatedTime(new Date())
                .build();
        // 执行插入方法
        appUserMapper.insert(user);
        return user;
    }

    @Override
    public AppUser getUser(String userId) {
        log.info("从数据库查询用户信息");
        return appUserMapper.selectByPrimaryKey(userId);
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(UpdateUserInfoBO updateUserInfoBO) {
        AppUser userInfo = new AppUser();
        BeanUtils.copyProperties(updateUserInfoBO,userInfo);

        userInfo.setUpdatedTime(new Date());
        userInfo.setActiveStatus(UserStatus.ACTIVE.type);

        int result = appUserMapper.updateByPrimaryKeySelective(userInfo);
        if (result != 1) {
            GraceException.display(ResponseStatusEnum.USER_UPDATE_ERROR);
        }

        String userId = updateUserInfoBO.getId();
        // 再次查询用户最新信息，放入redis中
        AppUser user = getUser(userId);
        redis.set(REDIS_USER_INFO + ":" +userId, JsonUtil.objectToJson(user));
    }
}