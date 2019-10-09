package com.curtain.other;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 延迟消息  时间轮   代替周期轮询
 *
 * @author Curtain
 * @date 2019/9/27 15:02
 */
public class RingBufferWheel {

    private Logger logger = LoggerFactory.getLogger(RingBufferWheel.class);

    /**
     * default size
     */
    private static final int STATIC_RING_SIZE = 64;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private Object[] ringBuffer;

    private int bufferSize;

    /**
     * thread pool
     */
    private ExecutorService executorService;

    private AtomicInteger taskSize = new AtomicInteger();

    /****
     * task running sign
     */
    private volatile boolean stop = false;

    private volatile boolean start = false;

    /**
     * total tick times
     */
    private AtomicInteger tick = new AtomicInteger();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public RingBufferWheel(ExecutorService executorService) {
        this.executorService = executorService;
        this.bufferSize = STATIC_RING_SIZE;
        this.ringBuffer = new Object[bufferSize];
    }

    public RingBufferWheel(ExecutorService executorService, int bufferSize) {
        this(executorService);
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Illegal initial bufferSize: " +
                    bufferSize);
        }
        this.bufferSize = tableSizeFor(bufferSize);
        this.ringBuffer = new Object[this.bufferSize];

    }

    static int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public void addTask(Task task) {
        int key = task.getKey();
        Set<Task> tasks = get(key);

        if (tasks != null) {
            int cycleNum = cycleNum(key, bufferSize);
            task.setCycleNum(cycleNum);
            tasks.add(task);
        } else {
            int index = mod(key, bufferSize);
            int cycleNum = cycleNum(key, bufferSize);
            task.setCycleNum(index);
            task.setCycleNum(cycleNum);
            Set<Task> sets = new HashSet<>();
            sets.add(task);
            put(key, sets);
        }
        taskSize.incrementAndGet();
    }

    public int taskSize() {
        return taskSize.get();
    }

    /**
     * Start background thread to consumer wheel timer, it will always run until you call method
     */
    public void start() {
        if (!start) {
            logger.info("delay task is starting");
            Thread job = new Thread(new TriggerJob());
            job.setName("consumer RingBuffer thread");
            job.start();
            start = true;
        }
    }

    /**
     * Stop consumer ring buffer therad
     *
     * @param force True whill force close consumer thread add discard all pending tasks
     *              otherwise the consumer thread waits for all tasks to completes before closing.
     */
    public void stop(boolean force) {
        if (force) {
            logger.info("delay task is forced stop");
            stop = true;
            executorService.shutdown();
        } else {
            logger.info("delay task is stopping");
            if (taskSize() > 0) {
                try {
                    lock.lock();
                    condition.await();
                    stop = true;
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                } finally {
                    lock.unlock();
                }
            }
            executorService.shutdown();
        }
    }

    private void put(int key, Set<Task> tasks) {
        int index = mod(key, bufferSize);
        ringBuffer[index] = tasks;
    }

    /**
     * 找到符合本次的任务  在ringBuffer删除这个任务   同时返回这个任务（因为即将被执行）
     *
     * @param key
     * @return
     */
    private Set<Task> remove(int key) {
        Set<Task> tempTask = new HashSet<>();
        Set<Task> result = new HashSet<>();

        Set<Task> tasks = (Set<Task>) ringBuffer[key];
        if (tasks == null) {
            return result;
        }

        for (Task task : tasks) {
            if (task.getCycleNum() == 0) {
                result.add(task);
                size2Notify();
            } else {
                task.setCycleNum(task.getCycleNum() - 1);
                tempTask.add(task);
            }
        }

        //update origin data
        ringBuffer[key] = tempTask;
        return result;
    }

    private void size2Notify() {
        try {
            lock.lock();
            int size = taskSize.decrementAndGet();
            if (size == 0) {
                condition.signal();
            }
        } finally {
            lock.unlock();
        }
    }

//    private boolean powerOf2(int target) {
//        if (target < 0) {
//            return false;
//        }
//        int value = target & (target - 1);
//        if (value != 0) {
//            return false;
//        }
//        return true;
//    }

    private int mod(int target, int mod) {
        // equals target % mod
        target = target + tick.get();
        return target & (mod - 1);
    }

    private int cycleNum(int target, int mod) {
        return target >> Integer.bitCount(mod - 1);
    }

    private Set<Task> get(int key) {
        int index = mod(key, bufferSize);

        return (Set<Task>) ringBuffer[index];
    }


    public abstract static class Task extends Thread {

        private int cycleNum;

        private int key;

        @Override
        public void run() {
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public void setCycleNum(int cycleNum) {
            this.cycleNum = cycleNum;
        }

        public int getCycleNum() {
            return cycleNum;
        }
    }

    private class TriggerJob implements Runnable {
        @Override
        public void run() {
            int index = 0;
            while (!stop) {
                Set<Task> tasks = remove(index);
                for (Task task : tasks) {
                    executorService.submit(task);
                }

                if (++index > bufferSize - 1) {
                    index = 0;

                }
                tick.incrementAndGet();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.error("InterruptedException", e);
                }
            }
            logger.info("delay task is stopped");
        }
    }
}
