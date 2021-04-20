package com.example.springbootfirstdemo.learn.fenbushi;

import redis.clients.jedis.Jedis;

import java.util.Collections;

// 单节点模式

/** https://www.cnblogs.com/moxiaotao/p/10829799.html
 * 首先，为了确保分布式锁可用，我们至少要确保锁的实现同时满足以下四个条件：
 *
 * 1互斥性。在任意时刻，只有一个客户端能持有锁。
 * 2不会发生死锁。即使有一个客户端在持有锁的期间崩溃而没有主动解锁，也能保证后续其他客户端能加锁。
 * 3具有容错性。只要大部分的Redis节点正常运行，客户端就可以加锁和解锁。
 * 4解铃还须系铃人。加锁和解锁必须是同一个客户端，客户端自己不能把别人加的锁给解了。
 */

public class RedisTool {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private static final Long RELEASE_SUCCESS = 1L;


    /**
     *  尝试获取分布式锁
     * @param jedis
     * @param key
     * @param requestId
     * @param expireTime
     * @return
     */
    public static Boolean tryGetDistributeLock(Jedis jedis, String key, String requestId, int expireTime){

        String result = jedis.set(key, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
        if(LOCK_SUCCESS.equals(result)){
             return true;
        }
        return false;
    }


    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     *
     * 第一行代码，我们写了一个简单的Lua脚本代码，
     * 第二行代码，我们将Lua代码传到jedis.eval()方法里，并使参数KEYS[1]赋值为lockKey，ARGV[1]赋值为requestId。eval()方法是将Lua代码交给Redis服务端执行
     *
     *
     * 那么这段Lua代码的功能是什么呢？其实很简单，首先获取锁对应的value值，检查是否与requestId相等，如果相等则删除锁（解锁）。
     * 那么为什么要使用Lua语言来实现呢？因为要确保上述操作是原子性的。
     * 那么为什么执行eval()方法可以确保原子性，
     *
     * 简单来说，就是在eval命令执行Lua代码的时候，Lua代码将被当成一个命令去执行，并且直到eval命令执行完成，Redis才会执行其他命令。
     *
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }


}
