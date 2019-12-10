package com.canthny.distribute.lock.redis.test;

import com.canthny.common.base.BaseResponse;
import com.canthny.distribute.lock.redis.RedisDistributeLocker;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.*;

/**
 * Description： redis分布式锁测试
 * Created By tanghao on 2019/12/9
 */
public class RedisDistributeLockTest extends BaseTests{

    @Autowired
    RedisDistributeLocker redisDistributeLocker;

    private Integer maxCount = new Integer(50);

    private CountDownLatch countDownLatch = new CountDownLatch(50);

    private static BlockingQueue blockingQueue = new ArrayBlockingQueue<Runnable>(100);

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("contact-pool-%d").build();
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, blockingQueue, namedThreadFactory);

    private final String redisKey = "DistributeLock_Redis";

    @Test
    public void test(){
        for(int i=0;i<50;i++){
            threadPoolExecutor.execute(()->{
                try{
                    System.out.println(String.format("Thread : %s start",Thread.currentThread().getName()));
                    BaseResponse response = redisDistributeLocker.lockWithRetry(redisKey,30L,100);
                    if(!response.isSuccess()){
                        System.out.println(String.format("Thread : %s get lock fail",Thread.currentThread().getName()));
                        return;
                    }
                    String lockId = response.getContent();
                    System.out.println(String.format("Thread : %s , get the lock : %s",Thread.currentThread().getName(),lockId));
                    maxCount--;
                    redisDistributeLocker.unlock(redisKey,lockId);
                    System.out.println(String.format("Thread : %s , release lock : %s",Thread.currentThread().getName(),lockId));
                }catch (Exception e){
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(maxCount);
    }
}
