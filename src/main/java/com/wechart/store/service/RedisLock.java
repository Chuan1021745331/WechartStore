package com.wechart.store.service;

/**
 * redis实现的分布式锁
 */
public interface RedisLock {

    /**
     * 获取锁
     * @param lockName 锁名称
     * @param requireTime 获取锁的时间，当超时则无法获取锁
     * @param expireTime 锁过期时间，防止死锁
     * @return 返回value值
     */
    String lock(String lockName, Integer requireTime, Integer expireTime);

    /**
     * 解锁
     * @param lockName 锁名称
     * @param requireId value值,防止其他客户端解锁
     * @return
     */
    boolean unlock(String lockName, String requireId);
}
