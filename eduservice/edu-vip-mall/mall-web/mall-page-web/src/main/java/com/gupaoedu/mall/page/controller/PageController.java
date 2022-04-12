package com.gupaoedu.mall.page.controller;

import com.gupaoedu.mall.page.service.PageService;
import com.gupaoedu.mall.util.RespResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * page 控制层
 *
 * @author Kang Yong
 * @date 2022/4/12
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/page")
public class PageController {

    @Autowired
    private PageService pageService;

    /**
     * 生成静态页面
     *
     * @param id {@link String}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/4/12
     */
    @GetMapping("/{id}")
    public RespResult html(@PathVariable(value = "id") String id) {
        try {
            this.pageService.html(id);
            log.info("===生成静态页面===SUCCESS");
        } catch (Exception e) {
            log.error("===生成静态页面===ERROR");
            e.printStackTrace();
        }
        return RespResult.ok();
    }

}
