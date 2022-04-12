package com.gupaoedu.mall.page.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * page
 *
 * @author Kang Yong
 * @date 2022/4/9
 * @since 1.0.0
 */
public interface PageService {

    /**
     * 生成静态页
     *
     * @param id {@link String}
     * @author Kang Yong
     * @date 2022/4/9
     */
    void html(String id) throws FileNotFoundException, UnsupportedEncodingException;

}
