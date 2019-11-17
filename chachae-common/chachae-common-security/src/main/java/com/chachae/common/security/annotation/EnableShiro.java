package com.chachae.common.security.annotation;

import com.chachae.common.security.config.ShiroAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * shiro 配置注入注解
 *
 * @author chachae
 * @date 2019/11/15 21:36
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShiroAutoConfiguration.class)
public @interface EnableShiro {}
