package com.chachae.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring MVC 配置
 *
 * @author chachae
 * @date 2019/11/15 21:32
 */
@EnableWebMvc
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

  /**
   * 同源跨域配置
   *
   * @param registry 注册对象
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedHeaders("*")
        .allowedOrigins("*")
        .allowCredentials(true)
        .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
        .maxAge(3600L);
  }
}
