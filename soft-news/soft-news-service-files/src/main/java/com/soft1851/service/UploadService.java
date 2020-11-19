package com.soft1851.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName 文件上传服务接口
 * @Description TODO
 * @Author hyj
 * @Date 2020/11/19
 **/
public interface UploadService {
    /**
     * fds上传
     * @param file   文件
     * @param fileExtName  扩展名
     * @return url
     * @throws Exception 异常
     */
    String uploadFds(MultipartFile file, String fileExtName) throws Exception;

    /**
     * OSS上传文件
     * @param file 文件
     * @param userId 用户ID
     * @param fileExtName 扩展名
     * @return 返回
     * @throws Exception 异常
     */
    public String uploadOSS(MultipartFile file,String userId, String fileExtName) throws  Exception;
}
