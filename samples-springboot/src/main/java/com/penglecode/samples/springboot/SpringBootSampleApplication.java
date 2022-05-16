package com.penglecode.samples.springboot;

import com.penglecode.samples.springboot.processor.MultiDataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author pengpeng
 * @version 1.0
 */
@MultiDataSource({"product", "order"})
@SpringBootApplication
public class SpringBootSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSampleApplication.class, args);
    }

}
