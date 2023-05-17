package com.example.demo.practice.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁/共享锁、排他锁
 *
 * @author dongxu
 * @create 2023-05-16 上午10:44
 */
public class SharedLockDemo {

    private int count = 0;
    /**
     * fair 是否是公平锁
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    public int readCount() {
        // 获取共享读锁
        readWriteLock.readLock().lock();
        try {
            return count;
        } finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }
    }

    public void increment() {
        // 获取独占写锁
        readWriteLock.writeLock().lock();
        try {
            count++;
        } finally {
            // 释放写锁
            readWriteLock.writeLock().unlock();
        }
    }
}
