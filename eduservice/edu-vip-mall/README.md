# Alibaba SpringCloud 

云商城后台项目

## 一、项目结构

* edu-vip-mall
    * doc 文档相关
    * mall-api（所有数据库表对应的Bean和Feign）
        * mall-goods-api（shop_goods对应的Api工程）
    * mall-gateway（管理所有微服务网关）
    * mall-service（所有微服务工程的父工程）
        * mall-goods-service（shop_goods数据库的增删改查）
    * mall-util（所有工具工程管理）
        * mall-common（工具工程）
        * mall-service-dependency（所有微服务工程公共依赖包、所有微服务工程公共配置类）
    * mall-web（管理涉及到页面渲染的工程）
    
    
## 二、技术栈

* [Docker容器](https://www.docker.org.cn/) 
    * docker是一个开源的软件部署解决方案
    * docker也是轻量级的应用容器架构
    * docker可以打包、发布、运行任何的应用
* [Nacos](https://nacos.io/zh-cn/docs/what-is-nacos.html)
    * 服务发现和服务健康监测
    * 动态配置服务
    * 动态 DNS 服务
    * 服务及其元数据管理  
* [Ceph](https://ceph.com/en/)
    * 分布式文件存储系统Ceph
    
