package cn.epsmart.recycling.device.base;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.company.project.android.utils.LogUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.greendao.database.Database;

import java.util.List;

import cn.epsmart.recycling.device.entity.DaoMaster;
import cn.epsmart.recycling.device.entity.DaoSession;
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
    private DaoSession daoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "fxhb");
        Database db = helper.getWritableDb();
        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();
        mHandler = new Handler();
        mMainThreadId = android.os.Process.myTid();
        LogUtils.init(null, true, true);
        SmartEvents.register(this);
        ForegroundCallbacks foregroundCallbacks=ForegroundCallbacks.init(this);
        foregroundCallbacks.addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {

            }

            @Override
            public void onBecameBackground() {

            }
        });
        registerActivityLifecycleCallbacks(foregroundCallbacks);
    }
    public DaoSession getDaoSession() {
        return daoSession;
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


}

