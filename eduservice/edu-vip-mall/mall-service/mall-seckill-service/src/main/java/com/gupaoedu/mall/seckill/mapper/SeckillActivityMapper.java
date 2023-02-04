package com.gupaoedu.mall.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gupaoedu.vip.mall.seckill.mode.SeckillActivity;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author KY
 * @description 针对表【seckill_activity】的数据库操作Mapper
 * @createDate 2023-02-03 22:53:42
 * @Entity generator.domain.SeckillActivity
 */
public interface SeckillActivityMapper extends BaseMapper<SeckillActivity> {

    /**
     * 有效活动时间查询
     *
     * @return {@link List< SeckillActivity>}
     * @author Kang Yong
     * @date 2023/2/4
     */
    @Select("SELECT * FROM seckill_activity WHERE end_time > NOW() ORDER BY start_time ASC LIMIT 5")
    List<SeckillActivity> validActivity();

}




