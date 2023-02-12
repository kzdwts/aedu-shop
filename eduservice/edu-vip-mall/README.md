# Alibaba SpringCloud 

云商城后台项目

## 一、项目结构

* edu-vip-mall
    * doc 文档相关
    * mall-api（所有数据库表对应的Bean和Feign）
        * mall-goods-api（shop_goods对应的Api工程）
        * mall-search-api（搜索服务api工程）
        * mall-page-api（商品详情页api工程）
        * mall-cart-api（购物车工程）
        * mall-user-api（用户工程）
        * mall-order-api（订单工程）
        * mall-pay-api（支付工程）
    * mall-gateway（管理所有微服务网关）
    * mall-service（所有微服务工程的父工程）
        * mall-goods-service（shop_goods数据库的增删改查）
        * mall-file-service（文件系统）
        * mall-canal-service（canal微服务）
        * mall-search-service（商品搜索工程）
        * mall-cart-service（购物车工程）
        * mall-user-service（用户工程）
        * mall-order-service（订单工程）
        * mall-pay-service（支付工程）
    * mall-util（所有工具工程管理）
        * mall-common（工具工程）
        * mall-service-dependency（所有微服务工程公共依赖包、所有微服务工程公共配置类）
    * mall-web（管理涉及到页面渲染的工程）
        * mall-page-web（商品详情）
        * mall-search-web（搜索前端页面工程）
    
    
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
* [OpenResty](http://openresty.org/cn/)    
    * OpenResty® 是一个基于 Nginx 与 Lua 的高性能 Web 平台
    * 用于方便地搭建能够处理超高并发、扩展性极高的动态 Web 应用、Web 服务和动态网关
    * 使用 Lua 脚本语言调动 Nginx 支持的各种 C 以及 Lua 模块
    * 足以胜任 10K 乃至 1000K 以上单机并发连接的高性能 Web 应用系统
    * OpenResty® 的目标是让你的Web服务直接跑在 Nginx 服务内部
    * [安装](http://openresty.org/cn/installation.html)
* [Lua](http://www.lua.org/docs.html)
  * [Lua中文官网](https://zhuanlan.zhihu.com/p/73147795)
  * Lua是一种强大，高效，轻量级，可嵌入的脚本语言
  * Lua是一种经过验证的，健壮的语言。
  * Lua是快速的
  * Lua是便携的
  * Lua可嵌入
  * Lua很强大（但很简单）
  * Lua是轻量级的
  * Lua是免费的
  * Lua特性
    * 一个小巧的脚本语言
    * 设计目的是为了通过灵活嵌入应用程序中从而为应用程序提供灵活的扩展和定制功能
    * 所有操作系统和平台上都可以编译、运行Lua脚本
    * 所有脚本引擎中，Lua的速度是最快的
  * 应用场景
    * 游戏开发
    * 独立应用脚本
    * 高性能web应用（天猫、京东都有应用） 
    * 扩展和数据库插件如：MySQL Proxy和MySQLWorkBench
* [Canal](https://github.com/alibaba/canal)    
    * 主要用途是基于 MySQL 数据库增量日志解析，提供增量数据订阅和消费
    * 工作原理
      * canal 模拟 MySQL slave 的交互协议，伪装自己为 MySQL slave ，向 MySQL master 发送dump 协议
      * MySQL master 收到 dump 请求，开始推送 binary log 给 slave (即 canal )
      * canal 解析 binary log 对象(原始为 byte 流)
    * MySQL主备复制原理
      * MySQL Master将数据变更写入二进制日志（binary log，其中记录叫做二进制日志事件binary log events，可以通过show binlog events进行查看）
      * MySQL slave将master的binary log events拷贝到它的中继日志（relay log）
      * MySQL salve重放 relay log中事件，将数据变更反映它自己的数据
* [MyBatis-Plus](https://baomidou.com/) 魂斗罗，MyBatis好兄弟      
* [ElasticSearch](https://www.elastic.co/cn/)      
    * [搜索服务](https://learnku.com/docs/elasticsearch73/7.3) 
    * [中文API](https://www.elastic.co/guide/cn/index.html)
* [MongoDB](https://www.mongodb.org.cn/)
    * MongoDB特点
        * MongoDB是一个面向文档存储的数据库，操作起来比较简单和容易
        * 你可以在MongoDB记录中设置任何属性的索引来实现更快的排序
        * MongoDB有很强的扩展性，海量数据处理时可以根据需要进行分片
        * MongoDB支持丰富的查询表达式
        * MongoDB中Map/Reduce主要是用来对数据进行批量处理和聚合操作
        * GridFS是MongoDB中的一个内置功能，可以用于存放大量小文件
        * MongoDB允许在服务端执行脚本，可以用JavaScript编写某个函数，直接在服务端执行，也可以把函数的定义存储在服务端，下次直接调用即可
        * MongoDB支持各种编程语言：Ruby，Python，Java，C++，PHP，C#等多种语言
        * MongoDB安装简单
* [seata](https://seata.io/zh-cn/index.html) 一款开源的分布式事务解决方案，致力于在微服务架构下提供高性能和简单易用的分布式事务服务        
    * [案例地址](https://github.com/seata/seata-samples)
    * 功能特色
      * 微服务框架支持
      * AT模式
      * TCC模式
      * SAGA模式
* AES算法库
  * [jdk1.8](https://www.oracle.com/java/technologies/javase-jce8-downloads.html)
  * 使用
    * 将两个算法包拷贝到jdk和jre的security目录下
* [ElasticJob](https://shardingsphere.apache.org/elasticjob/current/cn/overview/) 分布式调度解决方案
    * 功能列表
      * 弹性调度
        * 支持任务在分布式场景下的分片和高可用
        * 能够水平扩展任务的吞吐量和执行效率
        * 任务处理能力随资源配备性弹缩
      * 资源分配
      * 作业治理
      * 作业依赖
      * 作业开放生态
      * 可视化管控端
* [Redisson分布式锁](https://github.com/redisson/redisson/) 
* [Kafka](https://kafka.apache.org/)








