package com.gupaoedu.mall.search.controller;

import com.gupaoedu.mall.search.feign.SkuSearchFeign;
import com.gupaoedu.mall.util.RespResult;
import com.gupaoedu.mall.util.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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

    @Autowired
    private SkuSearchFeign skuSearchFeign;

    /**
     * 搜索页面跳转
     *
     * @return {@link String}
     * @author Kang Yong
     * @date 2022/3/14
     */
    @GetMapping
    public String search(Model model, @RequestParam Map<String, Object> searchMap) {
        // 搜索
        RespResult<Map<String, Object>> respResult = skuSearchFeign.search(searchMap);

        // 数据存入model
        model.addAttribute("result", respResult.getData());
        model.addAttribute("searchMap", searchMap);

        // 当前地址
        model.addAttribute("url", UrlUtils.map2url("/web/search", searchMap));
        // 没有排序参数的url
        model.addAttribute("urlsort", UrlUtils.replateUrlParameter(model.getAttribute("url").toString(), "sfield", "sm"));

        return "search";
    }
}
