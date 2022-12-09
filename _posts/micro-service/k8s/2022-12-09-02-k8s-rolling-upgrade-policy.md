---
layout:     post
title:      K8S滚动升级策略
date:       2022-12-09 13:35:40 +0800
postId:     2022-12-09-13-35-40
categories: [Docker]
keywords:   [Microservice, Docker]
---

K8S多节点部署状态下，采用滚动升级策略，可以保证服务稳定运行，平滑升级，流程如下：  
1) 新创建Pod节点N1，运行启动探测，直至启动成功  
2) N1启动成功后，开始接收流量  
3) 关闭老Pod节点O1，直至关闭结束  
4) 再创建一个Pod节点N2，重复以上流程，以此类推  

## 启动探测
不加启动探测的时间：
```
2022-12-02 17:33:00
2022-12-02 17:33:01
```

加启动探测的时间：
```
2022-12-02 17:21:03
2022-12-02 17:25:04
```

结论：需要配置启动探测，保证上一个Pod启动且Pod的JVM启动成功才会升级下一个Pod，
避免虽然所有Pod都是running状态但是JVM尚未启动成功，导致服务异常的问题。

![启动探测]({{ site.baseurl }}/image/post/2022/12/09/02/启动探测.png)

## 延迟时间
启动探测需要配置100秒延迟时间，避免JVM启动时间过长导致探测失败Pod重启，
这样就会导致Pod无限重启的失败情况。

另外，要注意，这里配置了启动探测时间，就没必要再配置最小准备时间了，
即便要配置的话，两个时间可以保持一致，如果不一致，那么两者中最小的那个时间会生效。

## 升级策略

想要达到文章开头的效果，使用如下的升级策略：
* 不可用Pod最大数量：0 个
* 超过期望的Pod数量：1 个

![升级策略]({{ site.baseurl }}/image/post/2022/12/09/02/升级策略.png)

能够使得：
1) 老Pod在新Pod启动成功之前不会销毁
2) 每次滚动升级1个Pod，1个成功之后销毁原Pod再新建一个新Pod

## 健康检查
除了配置启动探测之外，也需要配置健康检查，以保证服务出现问题的时候，Pod能够自动重启，
以修复异常的Pod节点服务。

### 健康检查接口
健康检查组件选择：Spring 提供的健康检查组件 或者 自己开发一个 RESTful 的测试接口。
spring组件的优点是集成度高、功能丰富；缺点是可能存在潜在的安全漏洞；
自研测试接口的优点是定制化程度高、安全；缺点是覆盖全部功能的话开发量大。

当前采用的是自研接口，只测试 `controller` 层接口是否准备完成；
如果采用 Spring 组件，那么需要在 `微服务网关层` 或者 `安全部门的WAF层` 屏蔽相关接口的访问。

### 各种检查的关系

1) 健康检查会在启动探测服务结束，也就是服务启动成功之后开始运行；  
2) 就绪检查和健康检查都会在服务运行期间持续调用，只有启动探测在启动成功之后就不再运行；   
3) 就绪检查的作用暂未体现出来，暂时不进行相关配置。  

## 参考资料
* [K8S滚动升级策略]({% post_url micro-service/k8s/2022-12-09-02-k8s-rolling-upgrade-policy %})

开始写作吧
```
![image-alter]({{ site.baseurl }}/image/post/2022/12/09/02/xxx.jpg)
```