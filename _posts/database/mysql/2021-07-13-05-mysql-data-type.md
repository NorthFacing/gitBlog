---
layout:     post
title:      MySQL - 数据类型
date:       2021-07-13 23:12:35 +0800
postId:     2021-07-13-23-12-35
categories: [database]
tags:       [database,MySQL]
geneMenu:   true
excerpt:    MySQL - 数据类型
---

## 数据类型

### 整数
* TINYINT     8 位
* SMALLINT    16位
* MEDIUMINT   24位
* INT         32位
* BIGINT      64位

整数类型有可选的 UNSIGNED 属性，表示不允许负值，这大致可以使整数的上限提高一倍。
有符号和无符号类型使用相同的存储空间，并具有相同的性能，因此可以根据实际情况选择合适的类型。

#### TINYINT
#### SMALLINT
#### MEDIUMINT
#### INT
#### BIGINT

### 实数
实数是带有小数部分的数字，然而，他们不只是为了存储小数部分，也可以使用DECIMAL存储比BIGINT
还大的整数。

### 字符串
* 小文本
    - VARCHAR 
    - CHAR
* 大文本
    - BLOB
    - TEXT

#### VARCHAR
VARCHAR 用于存储可变字符串，是最常⻅的字符串数据类型。它比 CHAR 更节省空间，因为它仅使用必要
的空间。VARCHAR 需要 1 或 2 个额外字节记录字符串⻓度，如果列的最大⻓度不大于 255 字节则只需
要 1 字节。VARCHAR 不会删除末尾空格。

VARCHAR 适用场景:字符串列的最大⻓度比平均⻓度大很多、列的更新很少、使用了 UTF8 这种复杂字符
集，每个字符都使用不同的字节数存储。

#### CHAR
CHAR 是定⻓的，根据定义的字符串⻓度分配足够的空间。CHAR 会删除末尾空格。

CHAR 适合存储很短的字符串，或所有值都接近同一个⻓度，例如存储密码的 MD5 值。对于经常变更的数
据，CHAR 也比 VARCHAR更好，因为定⻓的 CHAR 不容易产生碎片。对于非常短的列，CHAR 在存储空间
上也更有效率，例如用 CHAR 来存储只有 Y 和 N 的值只需要一个字节，但是 VARCHAR 需要两个字节，
因为还有一个记录⻓度的额外字节。

#### TEXT
字符类型

#### BLOB
二进制类型

### 日期和时间
* DATETIME
* TIMESTAMP

#### DATETIME
DATETIME 能保存大范围的值，从 1001~9999 年，精度为秒。把日期和时间封装到了一个整数中，与时
区无关，使用 8 字节存储空间。

#### TIMESTAMP
TIMESTAMP 和 UNIX 时间戳相同，只使用 4 字节的存储空间，范围比 DATETIME 小得多，只能表示 
1970 ~2038 年，并且依赖于时区。

### 位数据

### 选择标识符

### 特殊类型

## 选择策略

### 更小的通常更好
一般情况下，应该尽量使用可以正确存储数据的最小数据类型。更小的数据类型通常也更快，因为它们占用
更少的磁盘、内存和 CPU 缓存。

### 尽可能简单
简单数据类型的操作通常需要更少的 CPU 周期，例如整数比字符操作代价更低，因为字符集和校对规则使
字符相比整形更复杂。应该使用 MySQL 的内建类型 date、time 和 datetime 而不是字符串来存储日
期和时间，另一点是应该使用整形存储 IP 地址。

### 尽量避免 NULL
通常情况下最好指定列为 NOT NULL，除非需要存储 NULL值。因为如果查询中包含可为 NULL 的列对
MySQL 来说更难优化，可为 NULL 的列使索引、索引统计和值比较都更复杂，并且会使用更多存储空间。
当可为 NULL 的列被索引时，每个索引记录需要一个额外字节，在MyISAM 中还可能导致固定大小的索引
变成可变大小的索引。如果计划在列上建索引，就应该尽量避免设计成可为 NULL 的列。

## 其他问题

* VARCHAR 和 CHAR 的区别
* DATETIME 和 TIMESTAMP 的区别

## 参考资料

* [高性能MySQL](https://book.douban.com/subject/23008813/)