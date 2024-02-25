package com.anba.bakery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.function.Predicate;

@Configuration
public class SpringFoxConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.anba.bakery"))
//                .apis((RestController.class))
                .paths(PathSelectors.regex("(?!/error.*).*"))
                .build();
    }

    ApiInfo apiInfo() {
        return new ApiInfo("My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("General UserName",
                        "www.baeldung.com",
                        "user-name@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());

    }
}