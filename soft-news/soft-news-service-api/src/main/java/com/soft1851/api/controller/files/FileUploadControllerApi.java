package com.soft1851.api.controller.files;

import com.soft1851.pojo.bo.NewAdminBO;
import com.soft1851.result.GraceResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
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
     * 管理员人脸入库
     * @param username 用户名
     * @param multipartFile 文件
     * @return 返回
     * @throws Exception 异常
     */
    @ApiOperation(value = "管理员人脸入库",notes = "管理员人脸入库",httpMethod = "POST")
    @PostMapping("uploadToGridFS")
    GraceResult uploadToGridFs(@RequestParam String username,@RequestParam(value = "file") MultipartFile multipartFile);

    /**
     * 从gridFS中读取文件
     * @param faceId 人脸id
     * @param request 请求
     * @param response 响应
     * @throws Exception 异常
     */
    @ApiOperation(value = "从gridFS中读取文件",notes = "从gridFS中读取文件",httpMethod = "GET")
    @GetMapping("readInGridFS")
    GraceResult readInGridFs(@RequestParam String faceId,HttpServletRequest request,
                      HttpServletResponse response) throws Exception;
}

