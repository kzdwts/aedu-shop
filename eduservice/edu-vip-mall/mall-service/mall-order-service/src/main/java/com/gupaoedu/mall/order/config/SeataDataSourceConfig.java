package com.gupaoedu.mall.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 数据源
 *
 * @author Kang Yong
 * @date 2022/5/6
 * @since 1.0.0
 */
@Configuration
public class SeataDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 创建代理数据源
     *
     * @param druidDataSource {@link DataSource}
     * @return {@link DataSourceProxy}
     * @author Kang Yong
     * @date 2022/5/6
     */
    @Bean
    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * 替换MybatisSqlSessionFactoryBean的DataSource
     *
     * @param dataSourceProxy {@link DataSourceProxy}
     * @return {@link MybatisSqlSessionFactoryBean}
     * @author Kang Yong
     * @date 2022/5/6
     */
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则MyBatisPlus不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return mybatisSqlSessionFactoryBean;
    }
}
