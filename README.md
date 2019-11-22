# fast-auth-template 

## 简介

基于 SpringBoot2、JWT和Shiro实现的前后端分离快速开发框架，接口遵循RESTful风格，文档使用swagger，可用于权限管理系统的快速构建 

## 在线演示

[https://xxxx(待完善)](https://xxxx/)

- 账号：admin
- 密码：admin

## 特性

1. 使用MyBatis + TkMapper 作为持久层框架组合，简化开发
2. 使用Shiro + JWT 集认证、鉴权，抛弃传统的 session 认证
3. 抽离权限模块，实现可插拔配置，使用 EnableShiro 注解开启

> 更多特性持续更新

## 技术选型

| 描述       | 框架                | 版本          |
| ---------- | :------------------ | ------------- |
| 核心框架   | Spring Boot         | 2.2.0 RELEASE |
| 持久层框架 | MyBatis             | 2.1.1         |
| 持久层辅助 | TkMapper            | 4.1.5         |
| 连接池     | Druid               | 1.1.21        |
| 权限框架   | Shiro               | 1.4.1         |
| 身份认证   | JWT                 | 3.8.3         |
| 数据校验   | Hibernate-Validator | 6.1.0.Final   |
| 工具类库   | HuTool              | 5.0.6         |
| 工具类库   | Guava               | 28.1          |
| 工具类库   | joda-time           | 2.10.5        |
| 分页插件   | pageHelper          | 1.2.12        |
| 接口文档   | Swagger2            | 2.9.2         |
| 前端框架   | 待完善              | 1.1.0         |

## 导入项目

**环境准备：**

1. JDK1.8+
2. MySQL5.6+
3. Maven3.6+
4. Redis「可选」

**部署：**

1. clone 到本地：https://github.com/chachae/fast-auth-template.git
2. 使用 IDEA 选择 Open 导入项目；
3. 导入数据库到MySQL中，sql文件位于根目录的sql文件夹里面；
4. 确认application.properties 配置是否正确；
5. 启动项目，浏览器访问 http://localhost:8099/
