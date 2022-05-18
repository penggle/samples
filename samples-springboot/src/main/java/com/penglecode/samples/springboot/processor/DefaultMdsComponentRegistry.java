package com.penglecode.samples.springboot.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;

/**
 * @author pengpeng
 * @version 1.0
 */
public class DefaultMdsComponentRegistry implements BeanFactoryAware, ApplicationContextAware, EnvironmentAware {

    private ConfigurableEnvironment environment;

    private ConfigurableListableBeanFactory beanFactory;

    private ConfigurableApplicationContext applicationContext;

    public void registerBeanDefinitions(AnnotationMetadata mdsAnnotationMetadata, BeanDefinitionRegistry registry) {
        System.out.println(mdsAnnotationMetadata);
        System.out.println(registry);
        ConfigurableApplicationContext childApplicationContext = createChildApplicationContext();
        System.out.println(childApplicationContext);
        System.out.println(">>> dataSource1 = " + childApplicationContext.getBean(DataSource.class));
        DataSource dataSource = null;
        try {
            dataSource = beanFactory.getBean(DataSource.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        System.out.println(">>> dataSource2 = " + dataSource);
        try {
            dataSource = applicationContext.getBean(DataSource.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        System.out.println(">>> dataSource3 = " + dataSource);
    }

    protected ConfigurableApplicationContext createChildApplicationContext() {
        return new SpringApplicationBuilder()
                .child(DataSourceAutoConfiguration.class)
                .web(WebApplicationType.NONE)
                .bannerMode(Banner.Mode.OFF)
                .parent(applicationContext)
                .environment(environment)
                .run();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = (ConfigurableEnvironment) environment;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

}
