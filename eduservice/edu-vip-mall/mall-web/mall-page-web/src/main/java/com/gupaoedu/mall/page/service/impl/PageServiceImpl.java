package com.gupaoedu.mall.page.service.impl;

import com.alibaba.fastjson.JSON;
import com.gupaoedu.mall.goods.feign.CategoryFeign;
import com.gupaoedu.mall.goods.feign.SpuFeign;
import com.gupaoedu.mall.goods.model.Category;
import com.gupaoedu.mall.goods.model.Product;
import com.gupaoedu.mall.goods.model.Sku;
import com.gupaoedu.mall.page.service.PageService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

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
    public void html(String id) throws FileNotFoundException, UnsupportedEncodingException {
        // 加载数据
        Map<String, Object> dataMap = this.dataLoad(id);

        // 创建tyhmeleaf容器对象
        Context context = new Context();
        // 设置页面数据模型
        context.setVariables(dataMap);

        // 文件名字 id.html
        File dest = new File(pagepath, id + ".html");
        PrintWriter writer = new PrintWriter(dest, "UTF-8");

        // 生成页面
        this.templateEngine.process("item", context, writer);
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
        // 查询商品数据
        RespResult<Product> respResult = this.spuFeign.one(id);
        Product product = respResult.getData();

        // 如果查询到商品，处理下边的流程
        if (Objects.nonNull(product)) {
            // 数据模型
            Map<String, Object> dataMap = new HashMap<>();

            // 基础数据
            dataMap.put("spu", product.getSpu());
            dataMap.put("images", product.getSpu().getImages().split(","));
            dataMap.put("attrs", JSON.parseObject(product.getSpu().getAttributeList()));

            // 三级分类查询
            RespResult<Category> one = this.categoryFeign.one(product.getSpu().getCategoryOneId());
            RespResult<Category> two = this.categoryFeign.one(product.getSpu().getCategoryTwoId());
            RespResult<Category> three = this.categoryFeign.one(product.getSpu().getCategoryThreeId());
            dataMap.put("one", one);
            dataMap.put("two", two);
            dataMap.put("three", three);

            // Sku集合转JSON
            List<Sku> skus = product.getSkus();
            List<Map<String, Object>> skuList = new ArrayList<>();

            for (Sku sku : skus) {
                Map<String, Object> skuMap = new HashMap<>();
                skuMap.put("id", sku.getId());
                skuMap.put("price", sku.getPrice());
                skuMap.put("name", sku.getName());
                skuMap.put("attr", sku.getSkuAttribute());

                skuList.add(skuMap);
            }
            dataMap.put("skulist", skuList);
            return dataMap;
        }

        return null;
    }

}
