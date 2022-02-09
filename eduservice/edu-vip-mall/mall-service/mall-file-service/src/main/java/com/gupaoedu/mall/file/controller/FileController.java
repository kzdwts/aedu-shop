package com.gupaoedu.mall.file.controller;

import com.gupaoedu.mall.file.ceph.FileHandler;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件上传下载控制层
 *
 * @author Kang Yong
 * @date 2022/2/9
 * @since 1.0.0
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileHandler fileHandler;

    /**
     * 文件上传
     *
     * @param file {@link MultipartFile} 文件信息
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/2/9
     */
    @PostMapping("/upload")
    public RespResult upload(MultipartFile file) throws IOException {
        // 文件信息
        String filename = file.getOriginalFilename();
        byte[] buffer = file.getBytes();

        // 上传文件
        this.fileHandler.upload(filename, buffer);
        return RespResult.ok();
    }

    /**
     * 文件下载
     *
     * @param filename {@link String}
     * @author Kang Yong
     * @date 2022/2/9
     */
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse response) throws IOException {
        // 文件下载
        byte[] buffer = this.fileHandler.download(filename);

        // 输出
        ServletOutputStream os = response.getOutputStream();
        os.write(buffer);
        os.flush();
        os.close();
    }
}
