package cn.epsmart.recycling.device.utils;

import android.os.Handler;

import cn.epsmart.recycling.device.base.BaseApplication;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:23
 * @description: （添加一句描述）
 */
public class UIUtils {
    /**
     * 得到主线程id
     */
    public static int getMainThreadId() {
        return BaseApplication.getContext().getMainThreadId();
    }

    /**
     * 得到一个主线程的handler
     */
    public static Handler getMainThreadHandler() {
        return BaseApplication.getContext().getHandler();
    }

    /**
     * 安全的执行一个task
     */
    public static void postTaskSafely(Runnable task) {
        int curThreadId = android.os.Process.myTid();
        int mainThreadId = getMainThreadId();
        // 如果当前线程是主线程
        if (curThreadId == mainThreadId) {
            task.run();
        } else {
            // 如果当前线程不是主线程
            getMainThreadHandler().post(task);
        }
    }
}
