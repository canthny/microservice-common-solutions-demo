# 后端全栈技术支付系统

## 技术栈介绍
    zk监听主从切换，脑裂等情况

## 工程结构
    --客户端启动时连接master节点，获取整个master-slave信息；
    --开启线程循环ping主节点，超过一定时间ping失败则认为主节点异常；
    --遍历slave节点的info信息获取最新的主节点，若指定时间后还是没有主节点则认为整个主从redis宕了；
    --找到新的主节点后将host、port写入zk中的redis-master节点；
    --监听Watcher会发现zk的Node内容更新，从而切换当前redis客户端的连接，指向最新的master；
    --主从切换期间无法读写数据，待整个故障转移结束，回复主节点读写；
    
##第二版，拆分redis中间件
