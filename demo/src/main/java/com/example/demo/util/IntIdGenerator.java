package com.example.demo.util;

import java.util.concurrent.atomic.AtomicInteger;

public class IntIdGenerator {
    // 使用原子类保证线程安全
    private static final AtomicInteger counter = new AtomicInteger(0);

    // 最大ID值（防止溢出）
    private static final int MAX_ID = Integer.MAX_VALUE - 1;

    /**
     * 生成新的唯一ID
     * @return 不重复的整型ID
     * @throws IllegalStateException 当ID耗尽时抛出异常
     */
    public static synchronized int nextId() {
        int currentId = counter.getAndIncrement();

        // 检查是否达到最大值
        if (currentId >= MAX_ID) {
            throw new IllegalStateException("ID资源耗尽，无法生成新ID");
        }

        return currentId;
    }

    /**
     * 重置ID生成器（谨慎使用）
     * @param newStart 新的起始ID
     */
    public static synchronized void reset(int newStart) {
        counter.set(newStart);
    }

    /**
     * 获取当前ID值（不生成新ID）
     * @return 当前ID值
     */
    public static synchronized int currentId() {
        return counter.get();
    }
}
