package cn.epsmart.recycling.device.base;

import android.app.Application;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 17:58
 * @description: （添加一句描述）
 */
public class BaseApplication extends Application {
    private static BaseApplication mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
    public static BaseApplication getContext() {
        return mContext;
    }
}
