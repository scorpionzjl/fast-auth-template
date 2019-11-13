package com.chachae;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author chachae
 * @date 2019/11/12 22:14
 */
@SpringBootApplication
@MapperScan("com.chachae.dao")
public class StartApplication {

  public static void main(String[] args) {
    SpringApplication.run(StartApplication.class);
  }
}