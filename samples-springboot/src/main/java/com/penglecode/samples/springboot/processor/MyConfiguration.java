package com.penglecode.samples.springboot.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author pengpeng
 * @version 1.0
 */
@Configuration
public class MyConfiguration implements BeanFactoryAware, InitializingBean {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        DataSource dataSource = null;
        try {
            dataSource = beanFactory.getBean(DataSource.class);
        } catch (BeansException e) {
            //ignore
        }
        System.out.println(">>> dataSource = " + dataSource);
    }

}
