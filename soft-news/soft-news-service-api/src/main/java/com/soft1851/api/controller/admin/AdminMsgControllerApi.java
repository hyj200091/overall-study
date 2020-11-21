package com.soft1851.api.controller.admin;

import com.soft1851.pojo.bo.AdminLoginBO;
import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @ClassName AdminMsgControllerApi
 * @Description TODO
 * @Date 2020/11/20
 * @Version 1.0
 **/
@Api(value = "管理员维护", tags = {"管理员维护 controller"})
@RequestMapping("adminMsg")
public interface AdminMsgControllerApi {

    /**
     * 管理员登录
     *
     * @param adminLoginBO
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(value = "管理员登录", notes = "管理员登录", httpMethod = "POST")
    @PostMapping("/adminLogin")
    GraceResult adminLogin(@RequestBody AdminLoginBO adminLoginBO,
                           HttpServletRequest request,
                           HttpServletResponse response);


    /**
     * 查询管理员是否存在
     *
     * @param username 管理员用户名
     * @return GraceResult
     */
    @PostMapping("adminIsExist")
    @ApiOperation(value = "查询管理员是否存在", notes = "查询管理人员是否存在", httpMethod = "POST")
    GraceResult adminIsExist(@RequestParam String username);

    @PostMapping("addNewAdmin")
    @ApiOperation(value = "添加新的管理人员", notes = "添加新的管理人员", httpMethod = "POST")
    GraceResult addNewAdmin(HttpServletRequest request, HttpServletResponse response, @RequestBody NewAdminBO newAdminBO);

    /**
     * 分页查询管理员
     *
     * @param page     当前页码
     * @param pageSize 页数
     * @return 返回
     */
    @ApiOperation(value = "查询admin列表", notes = "查询admin列表", httpMethod = "POST")
    @PostMapping("/getAdminList")
    GraceResult getAdminList(@ApiParam(name = "page", value = "查询下一页的第几页")
                             @RequestParam Integer page,
                             @ApiParam(name = "pageSize", value = "分页查询每一页显示的条数")
                             @RequestParam Integer pageSize);


    /**
     * 退出登录
     *
     * @param adminId  管理员id
     * @param request  请求
     * @param response 响应
     * @return 返回
     */
    @ApiOperation(value = "管理员注销", notes = "管理员注销", httpMethod = "POST")
    @PostMapping("/adminLogout")
    GraceResult adminLogout(@RequestParam String adminId,
                            HttpServletRequest request,
                            HttpServletResponse response);
}

