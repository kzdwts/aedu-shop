package com.gupaoedu.mall.page.service.impl;

import com.gupaoedu.mall.goods.feign.CategoryFeign;
import com.gupaoedu.mall.goods.feign.SpuFeign;
import com.gupaoedu.mall.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.util.Map;

/**
 * page
 *
 * @author Kang Yong
 * @date 2022/4/9
 * @since 1.0.0
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private SpuFeign spuFeign;

    @Autowired
    private CategoryFeign categoryFeign;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${pagepath}")
    private String pagepath;

    /**
     * 生成静态页
     *
     * @param id {@link String}
     * @author Kang Yong
     * @date 2022/4/9
     */
    @Override
    public void html(String id) {
        // 加载数据
        Map<String, Object> dataMap = this.dataLoad(id);

        // 创建tyhmeleaf容器对象

        // 设置页面数据模型

        // 文件名字 id.html

        // 生成页面
    }

    /**
     * 数据加载
     *
     * @param id {@link String} 商品id，即商品spuid
     * @return {@link Map< String, Object>}
     * @author Kang Yong
     * @date 2022/4/9
     */
    private Map<String, Object> dataLoad(String id) {
        // TODO
        return null;
    }

}
