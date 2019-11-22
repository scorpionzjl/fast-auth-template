package com.chachae.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author chachae
 * @date 2019/11/22 16:49
 */
@Configuration
public class SwaggerConfig {

  @Bean
  public Docket createRestApi() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        // 标题
        .title("SpringBoot 快速构建工程")
        // 简介
        .description("")
        // 服务条款
        .termsOfServiceUrl("")
        // 作者个人信息
        .contact(new Contact("chachae", "https://github.com/chachae", "chachae@foxmail.com"))
        // 版本
        .version("2.0")
        .build();
  }
}
