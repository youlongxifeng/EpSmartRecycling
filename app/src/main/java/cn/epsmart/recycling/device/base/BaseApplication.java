package cn.epsmart.recycling.device.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.company.project.android.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

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
        LogUtils.init(null, true, true);
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

    //判断当前的应用程序是不是在运行
    //需要申请GETTask权限
    private boolean isApplicationBroughtToBackground() {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public boolean wasBackground = false;    //声明一个布尔变量,记录当前的活动背景

    @Override
    public void onPause() {
        super.onPause();
        if (isApplicationBroughtToBackground())
            wasBackground = true;
    }

    public void onResume() {
        super.onResume();
        if (wasBackground) {//
            Log.e("aa", "从后台回到前台");
        }
        wasBackground = false;
    }
}

}
