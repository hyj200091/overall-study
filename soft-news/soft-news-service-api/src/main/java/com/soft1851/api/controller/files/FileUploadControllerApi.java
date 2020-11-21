package com.soft1851.api.controller.files;

import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @ClassName FileUploadControllerApi
 * @Description 文件上传Api接口
 * @Date 2020/11/19
 * @Version 1.0
 **/
@Api(value = "文件上传 controller", tags = {"文件上传 controller"})
@RequestMapping("fs")
public interface FileUploadControllerApi {

    /**
     * 上传用户头像
     *
     * @param userId
     * @param file   文件对象
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "上传用户头像", notes = "上传用户头像", httpMethod = "POST")
    @PostMapping("/uploadFace")
    GraceResult uploadFace(@RequestParam String userId, MultipartFile file) throws Exception;

    /**
     * 上传多个文件
     *
     * @param userId
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadSomeFiles")
    GraceResult uploadSomeFile(@RequestParam String userId, MultipartFile[] files) throws Exception;

    /**
     * 文件上传到MongoDB的GridFS中
     * @param newAdminBO 入参
     * @param request 请求
     * @param response 响应
     * @return 返回
     * @throws Exception 异常
     */
    @ApiOperation(value = "上传到mongodb的GridFS",notes = "上传到mongodb的GridFS",httpMethod = "POST")
    @PostMapping("uploadToGridFS")
    GraceResult uploadToGridFs(@RequestBody NewAdminBO newAdminBO,
                               HttpServletRequest request,
                               HttpServletResponse response) throws Exception;
}

