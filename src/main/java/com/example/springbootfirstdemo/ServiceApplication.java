package com.example.springbootfirstdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication // 相当于 @Configuration、@EnableAutoConfiguration 、 @ComponentScan 三个的作用
/*
在启动类上使用@EnableAutoConfiguration注解，就会开启自动配置，
        简单点说就是它会根据定义在classpath下的类，自动的给你生成一些Bean，并加载到Spring的Context中。
*/

@MapperScan("com.example.springbootfirstdemo.mapper") // 只在和mybatis整合时使用
@ComponentScan(basePackages= {"com.example.springbootfirstdemo.*"}) // 扫描更精确
@EnableSwagger2
// 加上注解@EnableSwagger2 表示开启Swagger

public class ServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(ServiceApplication.class, args);
    }

//   查看都有哪些bean
    /*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }*/

}
