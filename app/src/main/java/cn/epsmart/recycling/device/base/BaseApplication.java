package cn.epsmart.recycling.device.base;

import android.app.Application;
import android.os.Handler;

import com.company.project.android.utils.LogUtils;

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
}
