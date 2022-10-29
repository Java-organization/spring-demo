package com.example.springdemo.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8080")
                .select()
                .paths(PathSelectors.regex(".*"))
                .apis(RequestHandlerSelectors.basePackage("com.example.springdemo.controller"))
                .build()
                .pathMapping("/")
                .globalOperationParameters(Collections.singletonList(getToken()))
                .produces(Collections.singleton(MediaType.APPLICATION_JSON_VALUE))
                .consumes(Collections.singleton(MediaType.APPLICATION_JSON_VALUE));
    }

    private Parameter getToken(){
        return  new ParameterBuilder()
                .name("authorization")
                .description("Token")
                .parameterType("header")
                .modelRef(new ModelRef("string"))
                .required(false)
                .build();
    }

}
