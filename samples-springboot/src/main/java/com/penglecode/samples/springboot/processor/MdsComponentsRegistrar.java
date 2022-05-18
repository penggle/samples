package com.penglecode.samples.springboot.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author pengpeng
 * @version 1.0
 */
public class MdsComponentsRegistrar implements ImportBeanDefinitionRegistrar, BeanFactoryAware {

    private ConfigurableListableBeanFactory beanFactory;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata mdsAnnotationMetadata, BeanDefinitionRegistry registry) {
        System.out.println("【MdsComponentRegistrar】==> registry1 = " + registry);
        System.out.println("【MdsComponentRegistrar】==> beanFactory1 = " + beanFactory);
        beanFactory.getBeanProvider(DefaultMdsComponentRegistry.class).ifAvailable(mdsComponentRegistry -> mdsComponentRegistry.registerBeanDefinitions(mdsAnnotationMetadata, registry));
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

}
