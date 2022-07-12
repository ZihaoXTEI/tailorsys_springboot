# 制衣店信息管理系统（后端）

### 项目简介

**后端框架**：Spring Boot、Spring

**持久层框架**：myBatis

**Java版本**：Java  *17.0.3*

**数据库**：mySQL

**数据分页工具**：pagehelper

**Excel导出工具**：easyexcel

## 系统功能模块设计

**事务安排模块**：事务安排模块共有事务日历和订单卡片两个子模块。事务月历子模块是通过用月历的形式展展示在当月的所有订单信息给用户，同时该事务月历还可以添加其它的安排，对于用户添加的安排事件，可以很方便地删除。订单卡片子模块是通过一张张卡片的形式展示每一张订单的每一阶段完成情况。

**订单管理模块**：共有新增订单、订单管理和顾客信息管理三个子模块。

**库存管理模块**：共有布料入库、布料库存管理和供应商信息管理三个子模块。

**数据管理模块**：共有服装类型数据、布料类型数据和服装用料数据三个子模块。

**数据统计模块**：共有库存统计和订单统计两个子模块。

**系统设置模块**：共有用户管理子模块、数据下载子模块和修改密码子模块。其中数据下载子模块允许用户将存储在数据库中的数据以Excel表格的形式下载至本地。

## 项目文件结构

```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─xtei
│  │  │          └─tailorsys
│  │  │              ├─config
│  │  │              │  └─jwt
│  │  │              ├─controller
│  │  │              │  ├─data
│  │  │              │  ├─file
│  │  │              │  ├─order
│  │  │              │  ├─plan
│  │  │              │  ├─statistics
│  │  │              │  ├─stock
│  │  │              │  └─system
│  │  │              ├─converter
│  │  │              ├─entity
│  │  │              │  ├─response
│  │  │              │  └─VO
│  │  │              ├─filter
│  │  │              ├─generator
│  │  │              ├─mapper
│  │  │              │  └─VO
│  │  │              ├─service
│  │  │              │  └─Impl
│  │  │              └─util
│  │  │                  └─pagehelper
│  │  └─resources
│  │      ├─mapper
│  │      │  └─VO
│  │      └─static
│  └─test
│      └─java
│          └─com
│              └─xtei
│                  └─tailorsys
└─target
    ├─classes
    │  ├─com
    │  │  └─xtei
    │  │      └─tailorsys
    │  │          ├─config
    │  │          │  └─jwt
    │  │          ├─controller
    │  │          │  ├─data
    │  │          │  ├─file
    │  │          │  ├─order
    │  │          │  ├─plan
    │  │          │  ├─statistics
    │  │          │  ├─stock
    │  │          │  └─system
    │  │          ├─converter
    │  │          ├─entity
    │  │          │  ├─response
    │  │          │  └─VO
    │  │          ├─filter
    │  │          ├─generator
    │  │          ├─mapper
    │  │          │  └─VO
    │  │          ├─service
    │  │          │  └─Impl
    │  │          └─util
    │  │              └─pagehelper
    │  └─mapper
    │      └─VO
    ├─generated-sources
    │  └─annotations
    ├─generated-test-sources
    │  └─test-annotations
    ├─maven-archiver
    ├─maven-status
    │  └─maven-compiler-plugin
    │      ├─compile
    │      │  └─default-compile
    │      └─testCompile
    │          └─default-testCompile
    ├─surefire-reports
    └─test-classes
        └─com
            └─xtei
                └─tailorsys
```

## 其它

数据返回前端类型：JSON

登录凭证：token（JWT）
