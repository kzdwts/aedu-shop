package com.gupaoedu.mall.search.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 搜索web 控制层
 *
 * @author Kang Yong
 * @date 2022/3/14
 * @since 1.0.0
 */
@Controller
@RequestMapping("/web/search")
public class SearchController {

    /**
     * 搜索页面跳转
     *
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/3/14
     */
    @GetMapping
    public String search() {
        return "search";
    }
}
