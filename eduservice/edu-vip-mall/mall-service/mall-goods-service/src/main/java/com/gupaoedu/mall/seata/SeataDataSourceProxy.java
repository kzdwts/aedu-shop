package com.gupaoedu.mall.seata;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import io.seata.rm.datasource.DataSourceProxy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * seata 代理数据源
 *
 * @author Kang Yong
 * @date 2022/7/24
 * @since 1.0.0
 */
@Configuration
public class SeataDataSourceProxy {

    /**
     * 获取数据源对象 DruidDataSource
     *
     * @return {@link DataSource}
     * @author Kang Yong
     * @date 2022/7/24
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }

    /**
     * 基于DruidDataSource 创建代理数据源DataSourceProxy
     *
     * @param druidDataSource {@link DataSource} 数据源
     * @return {@link DataSourceProxy} 代理数据源
     * @author Kang Yong
     * @date 2022/7/24
     */
    @Bean
    public DataSourceProxy dataSourceProxy(DataSource druidDataSource) {
        return new DataSourceProxy(druidDataSource);
    }

    /**
     * 替换MyBatis的数据源 （DataSourceProxy）
     *
     * @param dataSourceProxy {@link DataSource}
     * @return {@link MybatisSqlSessionFactoryBean}
     * @author Kang Yong
     * @date 2022/7/24
     */
    @Bean
    @ConfigurationProperties(prefix = "mybatis")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSourceProxy) {
        // 这里用 MybatisSqlSessionFactoryBean 代替了 SqlSessionFactoryBean，否则 MyBatisPlus 不会生效
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSourceProxy);
        return mybatisSqlSessionFactoryBean;
    }
}
