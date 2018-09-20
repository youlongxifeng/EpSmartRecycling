package cn.epsmart.recycling.device.base;

import android.app.Application;
import android.os.Handler;

import com.company.project.android.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import cn.epsmart.recycling.device.observer.SmartEvents;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 17:58
 * @description: （添加一句描述）
 */
public class BaseApplication extends Application {
    private static BaseApplication mContext;
    /**
     * 主线程 id
     */
    private int mMainThreadId;
    private Handler mHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        LogUtils.init(null,true,true);
        SmartEvents.register(this);
        SmartEvents.post(new Runnable() {
            @Override
            public void run() {
                LogUtils.i("=======================");
            }
        });
    }
    public static BaseApplication getContext() {
        return mContext;
    }
    public int getMainThreadId() {
        return mMainThreadId;
    }
    public Handler getHandler() {
        return mHandler;
    }


    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onEvent(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
