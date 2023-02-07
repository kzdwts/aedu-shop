package com.gupaoedu.canal.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring容器
 * <p>
 * 在动态定时任务作业中，由于作业对象没有交给Spring容器管理，无法使用注入注解，需要从Spring容
 * 器中手动获取
 *
 * @author Kang Yong
 * @date 2023/2/7
 * @since 1.0.0
 */
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext act;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        act = applicationContext;
    }

    public static <T> T getBean(Class clazz) {
        return act.getBean((Class<T>) clazz);
    }

}
