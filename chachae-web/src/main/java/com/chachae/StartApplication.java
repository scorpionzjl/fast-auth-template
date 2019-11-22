package com.chachae;

import com.chachae.common.security.annotation.EnableShiro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chachae
 * @date 2019/11/12 22:14
 */
@EnableShiro
@EnableSwagger2
@EnableWebMvc
@SpringBootApplication
@MapperScan("com.chachae.dao")
public class StartApplication {

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class);
  }
}
