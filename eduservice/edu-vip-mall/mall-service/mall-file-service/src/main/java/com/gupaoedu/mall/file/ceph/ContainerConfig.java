package com.gupaoedu.mall.file.ceph;

import lombok.Data;
import org.javaswift.joss.client.factory.AccountConfig;
import org.javaswift.joss.client.factory.AccountFactory;
import org.javaswift.joss.client.factory.AuthenticationMethod;
import org.javaswift.joss.model.Account;
import org.javaswift.joss.model.Container;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ceph配置
 *
 * @author Kang Yong
 * @date 2022/2/9
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ceph")
public class ContainerConfig {

    // 自用户名
    private String username;

    // 秘钥
    private String password;

    // 接口访问路径
    private String authUrl;

    // 默认容器名字
    private String defaultContainerName;

    /**
     * ceph 账号信息配置
     *
     * @return
     */
    @Bean
    public Account account() {
        AccountConfig accountConfig = new AccountConfig();
        accountConfig.setUsername(username);
        accountConfig.setPassword(password);
        accountConfig.setAuthUrl(authUrl);
        accountConfig.setAuthenticationMethod(AuthenticationMethod.BASIC);
        return new AccountFactory(accountConfig).createAccount();
    }

    /**
     * 容器对象
     *
     * @return
     */
    @Bean
    public Container container() {
        Container container = account().getContainer(defaultContainerName);
        if (!container.exists()) {
            container.create();
        }
        return container;
    }

}
