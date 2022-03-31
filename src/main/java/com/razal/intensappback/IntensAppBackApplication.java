package com.razal.intensappback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class IntensAppBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(IntensAppBackApplication.class, args);
        //Arrays.stream(context.getBeanDefinitionNames()).forEach(s -> System.out.println("Bean: "+s));
    }

}
