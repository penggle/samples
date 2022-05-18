package com.penglecode.samples.springboot.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengpeng
 * @version 1.0
 */
@Configuration
public class MdsComponentRegistryConfiguration {

    @Bean
    public DefaultMdsComponentRegistry defaultMdsComponentRegistry() {
        return new DefaultMdsComponentRegistry();
    }

}
