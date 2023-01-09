package com.frankzhou.datastandard.redis;

/**
 * @author: this.FrankZhou
 * @date: 2022/1/2
 * @description: redis分布式锁
 */
public interface DistributedLock {

    /**
     * 尝试获取分布式锁
     */
    boolean tryLock(long timeSec);

    /**
     * 释放分布式锁
     */
    void unLock();
}
