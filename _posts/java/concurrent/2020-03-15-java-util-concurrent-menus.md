---
layout:     post
title:      Java并发包学习汇总目录
date:       2020-03-15 22:28:27 +0800
postId:     2020-03-15-22-28-28
categories: [concurrent]
keywords:   [concurrent]
---

## 基本并发类

* Thread 类: 该类描述了执行并发 Java 应用程序的所有线程。
* Runnable 接口: 这是 Java 中创建并发应用程序的另一种方式。
* ThreadLocal 类: 该类用于存放从属于某一线程的变量。
* ThreadFactory 接口: 这是实现 Factory 设计模式的基类，你可以用它来创建定制线程。

详见：[Java并发包 ——  Thread 和 Runnable]({% post_url java/concurrent/2020-03-22-java-util-concurrent-thread-runnable %})


## 同步机制

Java 并发 API 包括多种同步机制，可以支持你：
* 定义用于访问某一共享资源的临界段;
* 在某一共同点上同步不同的任务。

下面是最重要的同步机制：
### synchronized 关键字
synchronized 关键字允许你在某个代码块或者某个完整的方法中定义一个临界段。

### Lock 接口
Lock 提供了比 synchronized 关键字更为灵活的同步操作。Lock 接口有多种不同类型: ReentrantLock 用于实现一个可与某种条件相关联的锁; ReentrantRead- WriteLock 将读写操作分离开来; StampedLock 是 Java 8 中增加的一种新特性，它包括三种 控制读/写访问的模式。

### Semaphore 类
该类通过实现经典的信号量机制来实现同步。Java 支持二进制信号量和一般 信号量。

### CountDownLatch 类
该类允许一个任务等待多项操作的结束。

### CyclicBarrier 类
该类允许多线程在某一共同点上进行同步。

### Phaser类
该类允许你控制那些分割成多个阶段的任务的执行。在所有任务都完成当前阶段之前，任何任务都不能进入下一阶段

## 执行器

执行器框架是在实现并发任务时将线程的创建和管理分割开来的一种机制。你不必担心线程的创 建和管理，只需要关心任务的创建并且将其发送给执行器。执行器框架另一个重要的优势是 Callable 接口。它类似于 Runnable 接口，但是却提供了两方面的增强：
* 这个接口的主方法名称为 call() ，可以返回结果。
* 当发送一个 Callable 对象给执行器时，将获得一个实现了 Future 接口的对象。可以使用这个对象来控制 Callable 对象的状态和结果。

该框架中涉及的主要类如下：

### Executor 接口和 ExecutorService 接口
它们包含了所有执行器共有的 execute()方法。

### ThreadPoolExecutor 类
该类允许你获取一个含有线程池的执行器，而且可以定义并行任务的最大数目。

### ScheduledThreadPoolExecutor 类
这是一种特殊的执行器，可以使你在某段延迟之后执行任务或者周期性执行任务。

### Executors
该类使执行器的创建更为容易。

### Callable 接口
这是 Runnable 接口的替代接口——可返回值的一个单独的任务。

### Future 接口
该接口包含了一些能获取 Callable 接口返回值并且控制其状态的方法。


详见：[Java并发包 ScheduledExecutorService]({% post_url java/concurrent/2020-02-09-java-util-concurrent-ScheduledExecutorService %})

## Fork/Join 框架
Fork/Join 框架定义了一种特殊的执行器，尤其针对采用分治方法进行求解的问题。针对解决这类 问题的并发任务，它还提供了一种优化其执行的机制。Fork/Join 是为细粒度并行处理量身定制的，因 为它的开销非常小，这也是将新任务加入队列中并且按照队列排序执行任务的需要。该框架涉及的主 要类和接口如下。

### ForkJoinPool
该类实现了要用于运行任务的执行器。

### ForkJoinTask
这是一个可以在 ForkJoinPool 类中执行的任务。

### ForkJoinWorkerThread
这是一个准备在 ForkJoinPool 类中执行任务的线程。

## 并行流

流和 lambda 表达式可能是 Java 8 中最重要的两个新特性。流已经被增加为 Collection 接口和 9 其他一些数据源的方法，它允许处理某一数据结构的所有元素、生成新的结构、筛选数据和使用 MapReduce 方法来实现算法。

并行流是一种特殊的流，它以一种并行方式实现其操作。使用并行流时涉及的最重要的元素如下。

### Stream 接口
该接口定义了所有可以在一个流上实施的操作。

### Optional
这是一个容器对象，可能(也可能不)包含一个非空值。

### Collectors
该类实现了约简(reduction)操作，而该操作可作为流操作序列的一部分使用。

### lambda 表达式
流被认为是可以处理 lambda 表达式的。大多数流方法都会接收一个 lambda 表达式作为参数，这让你可以实现更为紧凑的操作。

## 并发数据结构
Java API 中的常见数据结构(例如 ArrayList、Hashtable 等)并不能在并发应用程序中使用，除非采用某种外部同步机制。但是如果你采用了某种同步机制，应用程序就会增加大量的额外计算时 间。而如果你不采用同步机制，那么应用程序中很可能出现竞争条件。如果你在多个线程中修改数据， 那么就会出现竞争条件，你可能会面对各种异常(例如 ConcurrentModificationException 和 ArrayIndexOutOfBoundsException)，出现隐性数据丢失，或者应用程序会陷入死循环。

Java 并发 API 中含有大量可以在并发应用中使用而没有风险的数据结构。我们将它们分为以下两大类别：
* 阻塞型数据结构：这些数据结构含有一些能够阻塞调用任务的方法，例如，当数据结构为空 而你又要从中获取值时。
* 非阻塞型数据结构：如果操作可以立即进行，它并不会阻塞调用任务。否则，它将返回 null 值或者抛出异常。

### ConcurrentLinkedDeque
这是一个非阻塞型的列表。

### ConcurrentLinkedQueue
这是一个非阻塞型的队列。
### LinkedBlockingDeque
这是一个阻塞型的列表

### LinkedBlockingQueue
这是一个阻塞型的队列。

### PriorityBlockingQueue
这是一个基于优先级对元素进行排序的阻塞型队列。

### ConcurrentSkipListMap
这是一个非阻塞型的 NavigableMap。

### ConcurrentHashMap
这是一个非阻塞型的哈希表。

### Atomic相关
这些是基本 Java 数据类型的原子实现：
* AtomicBoolean
* AtomicInteger
* AtomicLong
* AtomicReference

## 并发设计模式

在软件工程中，设计模式是针对某一类共同问题的解决方案。这种解决方案被多次使用，而且已 经被证明是针对该类问题的最优解决方案。每当你需要解决这其中的某个问题，就可以使用它们来避 免做重复工作。其中，单例模式(Singleton)和工厂模式(Factory)是几乎每个应用程序中都要用到 的通用设计模式。
并发处理也有其自己的设计模式。本节，我们将介绍一些最常用的并发设计模式，以及它们的 Java 语言实现。

### 信号模式
这种设计模式介绍了如何实现某一任务向另一任务通告某一事件的情形。实现这种设计模式最简 单的方式是采用信号量或者互斥，使用 Java 语言中的 ReentrantLock 类或 Semaphore 类即可，甚 至可以采用 Object 类中的 wait()方法和 notify()方法。

### 会合模式
这种设计模式是信号模式的推广。在这种情况下，第一个任务将等待第二个任务的某一事件，而 第二个任务又在等待第一个任务的某一事件。其解决方案和信号模式非常相似，只不过在这种情况下， 你必须使用两个对象而不是一个。

### 互斥模式
互斥这种机制可以用来实现临界段，确保操作相互排斥。这就是说，一次只有一个任务可以执行 由互斥机制保护的代码片段。在 Java 中，你可以使用 synchronized 关键字(这允许你保护一段代 码或者一个完整的方法)、ReentrantLock 类或者 Semaphore 类来实现一个临界段。

### 多元复用模式
多元复用设计模式是互斥机制的推广。在这种情形下，规定数目的任务可以同时执行临界段。这 很有用，例如，当你拥有某一资源的多个副本时。在 Java 中实现这种设计模式最简单的方式是使用 Semaphore 类，并且使用可同时执行临界段的任务数来初始化该类。

### 栅栏模式
这种设计模式解释了如何在某一共同点上实现任务同步的情形。每个任务都必须等到所有任务 都到达同步点后才能继续执行。Java 并发 API 提供了 CyclicBarrier 类，它是这种设计模式的一 个实现。

### 双重检查锁定模式
当你获得某个锁之后要检查某项条件时，这种设计模式可以为解决该问题提供方案。如果该条件 为假，你实际上也已经花费了获取到理想的锁所需的开销。对象的延迟初始化就是针对这种情形的例 子。如果你有一个类实现了单例设计模式，那可能会有如下这样的代码。

### 读-写锁模式
当你使用锁来保护对某个共享变量的访问时，只有一个任务可以访问该变量，这和你将要对该变 量实施的操作是相互独立的。有时，你的变量需要修改的次数很少，却需要读取很多次。这种情况下， 12 锁的性能就会比较差了，因为所有读操作都可以并发进行而不会带来任何问题。为解决这样的问题， 出现了读写锁设计模式。这种模式定义了一种特殊的锁，它含有两个内部锁:一个用于读操作，而 另一个用于写操作。该锁的行为特点如下所示。

* 如果一个任务正在执行读操作而另一任务想要进行另一个读操作，那么另一任务可以进行该 操作。
* 如果一个任务正在执行读操作而另一任务想要进行写操作，那么另一任务将被阻塞，直到所 有的读取方都完成操作为止。
* 如果一个任务正在执行写操作而另一任务想要执行另一操作(读或者写)，那么另一任务将被 阻塞，直到写入方完成操作为止。

Java 并发 API 中含有 ReentrantReadWriteLock 类，该类实现了这种设计模式。如果你想从头 开始实现该设计模式，就必须非常注意读任务和写任务之间的优先级。如果有太多读任务存在，那么 写任务等待的时间就会很长。

### 线程池模式
这种设计模式试图减少为执行每个任务而创建线程所引入的开销。该模式由一个线程集合和一个 待执行的任务队列构成。线程集合通常具有固定大小。当一个线程完成了某个任务的执行时，它本身 并不会结束执行，它要寻找队列中的另一个任务。如果存在另一个任务，那么它将执行该任务。如果 不存在另一个任务，那么该线程将一直等待，直到有任务插入队列中为止，但是线程本身不会被终结。
Java 并发 API 包含一些实现 ExecutorService 接口的类，该接口内部采用了一个线程池。

### 线程局部存储模式
这种设计模式定义了如何使用局部从属于任务的全局变量或静态变量。当在某个类中有一个静态 属性时，那么该类的所有对象都会访问该属性的同一存在。如果使用了线程局部存储，则每个线程都 会访问该变量的一个不同实例。
Java 并发 API 包含了 ThreadLocal 类，该类实现了这种设计模式。

## 参考资料

* 书籍
    - [9.1' - Concurrent Programming in Java](https://book.douban.com/subject/1440218/)
    - [8.6' - 图解Java多线程设计模式](https://book.douban.com/subject/27116724/)
    - [8.2' - 精通Java并发编程（第二版）]({% post_url book/2020-09-15-mastering-concurrency-programming-with-java9-2th-edition %})

* 文章
    - [如何学习Java多线程](https://zhuanlan.zhihu.com/p/35382932)
    - [Java Concurrency and Multithreading Tutorial](http://tutorials.jenkov.com/java-concurrency/index.html)
    - [Java Concurrency Utilities - ScheduledExecutorService](http://tutorials.jenkov.com/java-util-concurrent/scheduledexecutorservice.html)
    - [Java concurrency (multi-threading) - Tutorial](https://www.vogella.com/tutorials/JavaConcurrency/article.html)

