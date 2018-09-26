package cn.epsmart.recycling.device.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;

import cn.epsmart.recycling.device.base.BaseApplication;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 10:23
 * @description: （添加一句描述）
 */
public class UIUtils {

    public static Resources getResources() {
        return BaseApplication.getContext().getResources();
    }
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

    /**
     * dp 转像素
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 得到string.xml中的一个字符串
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }
}
