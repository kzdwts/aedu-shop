package com.gupaoedu.mall.file.ceph;

import org.javaswift.joss.model.Container;
import org.javaswift.joss.model.StoredObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 文件上传下载工具
 *
 * @author Kang Yong
 * @date 2022/2/9
 * @since 1.0.0
 */
@Component
public class FileHandler {

    // 容器
    @Autowired
    private Container container;

    /**
     * 文件上传
     *
     * @param filename {@link String} 文件名
     * @param buffer   {@link byte[]} 文件二进制流
     * @author Kang Yong
     * @date 2022/2/9
     */
    public void upload(String filename, byte[] buffer) {
        // 获取容器
        StoredObject storedObject = container.getObject(filename);
        // 上传文件
        storedObject.uploadObject(buffer);
    }

    /**
     * 文件下载
     *
     * @param filename {@link String} 文件名
     * @return {@link byte[]} 文件二进制流
     * @author Kang Yong
     * @date 2022/2/9
     */
    public byte[] download(String filename) {
        // 获取容器
        StoredObject storedObject = container.getObject(filename);
        // 下载文件
        byte[] bytes = storedObject.downloadObject();
        return bytes;
    }

}
