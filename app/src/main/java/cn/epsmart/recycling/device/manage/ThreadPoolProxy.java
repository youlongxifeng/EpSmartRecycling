package cn.epsmart.recycling.device.manage;

import com.company.project.android.utils.LogUtils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/25 10:37
 * @description: （线程池代理）
 */
public class ThreadPoolProxy {
    ThreadPoolExecutor mExecutor;
    int mCorePoolSize;
    int mMaximumPoolSize;
    long mKeepAliveTime;

    public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
        mCorePoolSize = corePoolSize;
        mMaximumPoolSize = maximumPoolSize;
        mKeepAliveTime = keepAliveTime;
    }
    /**
     *  初始化ThreadPoolExecutor
     */
    private void initTThreadPoolExecutor() {// 双重间检查加锁
        if (mExecutor == null) {
            synchronized (ThreadPoolProxy.class) {
                if (mExecutor == null) {
                    TimeUnit unit = TimeUnit.MICROSECONDS;
                    BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
                    java.util.concurrent.ThreadFactory threadFactory = Executors
                            .defaultThreadFactory();
                    RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

                    mExecutor = new ThreadPoolExecutor(mCorePoolSize,// 核心线程数
                            mMaximumPoolSize,// 最大线程数
                            mKeepAliveTime, // 保持时间
                            unit, // 保持时间的单位
                            workQueue, // 工作队列
                            threadFactory, // 线程工厂
                            handler// 异常捕获器
                    );
                }
            }
        }
    }

    /**
     * 执行任务
     */
    public void execute(Runnable task) {
        initTThreadPoolExecutor();
        mExecutor.execute(task);
    }

    /**
     * 提交任务
     */
    public Future<?> submit(Runnable task) {
        initTThreadPoolExecutor();
        return mExecutor.submit(task);
    }

    /**
     * 移除任务
     */
    public void remove(Runnable task) {
        initTThreadPoolExecutor();
        mExecutor.getQueue().remove(task);
        LogUtils.i("移除任务--" + task);
    }
}
