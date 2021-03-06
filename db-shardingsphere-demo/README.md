### 工程说明

**sharding-jdbc-springboot-demo**

    按日期分库，按userId分表，使用复杂分片策略，详见com.canthny.shardingsphere.sharding.jdbc.demo.stratege包中的CreatedDateShardingDbStrategy等
    tables_mysql.sql：建表语句，示例数据库为order_2018、order_2019自行创建
    BaseTests：@ActiveProfiles("sharding-db-table")选择激活的分库分表配置，application-sharding-table.properties仅分表，application-sharding-db-table.properties分库分表
    OrderInsertTest：测试订单插入
    OrderQueryTest：测试订单查询
    OrderQueryPageTest：测试订单分页查询

### ShardingSphere学习笔记

    解析引擎：解析规则（抽象语法树）、提取规则、填充规则、优化规则
    路由引擎：分片路由、广播路由（不携带分配键的sql）
    改写引擎：逻辑库/表指向物理库表，缺失字段补充，分布式下的均值不能用(avg1+avg2+avg3)应该改写为(sum1+sum2+sum3)/(count1+count2+count3)
    执行引擎：
        内存限制模式————执行sql操作多实例多表时，对每张表创建一个新的数据库连接，多线程并行处理执行效率最大化，且防止内存溢出或垃圾回收频繁，流式归并（记录游标，需要使用结果的时候再根据游标加载），
        连接限制模式————严格控制数据库连接数，真对每个库的操作创建一个连接，防止一次请求占用过多数据库连接，但是一个连接串行处理，需要将当前查询结果集加在至内存
        自动化执行引擎————根据配置的maxConnectionSizePerQuery自动选择连接模式，并且在下一次执行的准备阶段就原子性的获取本次SQL请求的全部数据库连接（仅在内存限制模式下，例如：AB查询都对应一个库的两个分表，A查询占了1个连接，B查询占了1个连接，数据库最大2个连接，就会形成死锁）
    
    归并引擎：排序归并、分组归并、聚合归并、分页归并