package com.gupaoedu.mall.goods.controller;

import com.gupaoedu.mall.goods.model.Brand;
import com.gupaoedu.mall.goods.service.BrandService;
import com.gupaoedu.mall.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import javax.naming.spi.ResolveResult;
import java.util.List;

/**
 * 品牌 控制层
 *
 * @author Kang Yong
 * @date 2022/1/21
 * @since 1.0.0
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * 新增
     *
     * @param brand {@link Brand}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/21
     */
    @PostMapping
    public RespResult add(@RequestBody Brand brand) {
        boolean save = brandService.save(brand);
        return RespResult.ok();
    }

    /**
     * 修改
     *
     * @param brand {@link Brand}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/21
     */
    @PutMapping
    public RespResult update(@RequestBody Brand brand) {
        brandService.updateById(brand);
        return RespResult.ok();
    }

    /**
     * 删除
     *
     * @param id {@link Brand}
     * @return {@link RespResult}
     * @author Kang Yong
     * @date 2022/1/21
     */
    @DeleteMapping("/{id}")
    public RespResult delete(@PathVariable("id") Integer id) {
        brandService.removeById(id);
        return RespResult.ok();
    }

    /**
     * 根据分类id查询品牌
     *
     * @param categoryId {@link Integer}
     * @return {@link RespResult< List< Brand>>}
     * @author Kang Yong
     * @date 2022/2/10
     */
    @GetMapping("/category/{categoryId}")
    public RespResult<List<Brand>> queryByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        List<Brand> brandList = this.brandService.queryByCategoryId(categoryId);
        return RespResult.ok(brandList);
    }
}
