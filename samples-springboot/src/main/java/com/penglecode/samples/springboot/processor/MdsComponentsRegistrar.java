package com.penglecode.samples.springboot.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;

/**
 * @author pengpeng
 * @version 1.0
 */
@Configuration(proxyBeanMethods=false)
public class MdsComponentsRegistrar implements ApplicationContextInitializer<ConfigurableApplicationContext>, ImportBeanDefinitionRegistrar, BeanDefinitionRegistryPostProcessor, BeanFactoryAware, ApplicationContextAware, PriorityOrdered {

    private ConfigurableListableBeanFactory beanFactory;

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        AnnotationConfigRegistry annotationConfigRegistry = (AnnotationConfigRegistry) applicationContext;
        annotationConfigRegistry.scan("org.springframework.boot.autoconfigure.context");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        System.out.println("【MdsComponentRegistrar】==> registry1 = " + registry);
        System.out.println("【MdsComponentRegistrar】==> beanFactory1 = " + beanFactory);
        System.out.println("【MdsComponentRegistrar】==> applicationContext1 = " + applicationContext);
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanPostProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);
        for(String beanPostProcessorName : beanPostProcessorNames) {
            beanFactory.addBeanPostProcessor(beanFactory.getBean(beanPostProcessorName, BeanPostProcessor.class));
        }

        System.out.println("【MdsComponentsRegistrar】==> registry2 = " + registry);
        System.out.println("【MdsComponentsRegistrar】==> beanFactory2 = " + beanFactory);
        System.out.println("【MdsComponentsRegistrar】==> applicationContext2 = " + applicationContext);
        DataSource dataSource = beanFactory.getBean("shardingSphereDataSource", DataSource.class);
        System.out.println("【MdsComponentsRegistrar】==> dataSource = " + dataSource);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 2000;
    }

}
