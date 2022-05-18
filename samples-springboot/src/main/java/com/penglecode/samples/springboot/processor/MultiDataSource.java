package com.penglecode.samples.springboot.processor;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author pengpeng
 * @version 1.0
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({MdsComponentsRegistrar.class, MdsComponentRegistryConfiguration.class})
public @interface MultiDataSource {

    String[] value();

}
