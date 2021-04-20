package com.example.springbootfirstdemo.learn.fenbushi;


import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

// 集群模式
// https://blog.csdn.net/asd051377305/article/details/108384490?utm_medium=distribute.pc_relevant.none-task-blog-baidujs_baidulandingword-0&spm=1001.2101.3001.4242

@Configuration
public class RedisConfig {

    @Bean
    public void getClient() {

        Config config = new Config();

        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);

        RedissonClient redisson = Redisson.create(config);

        RLock lock = redisson.getLock("mykey");

        try{
            // 1. 最常见的使用方法
            //lock.lock();
            // 2. 支持过期解锁功能,10秒钟以后自动解锁, 无需调用unlock方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);

            // 3. 尝试加锁，最多等待2秒，上锁以后8秒自动解锁
            boolean res = lock.tryLock(2, 8, TimeUnit.SECONDS);
//            lock.tryLock((long)waitTimeout, (long)leaseTime, TimeUnit.SECONDS);
            if(res){ //成功
                //处理业务
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }

    }


}
