package cn.epsmart.recycling.device.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.RequiresPermission;

import cn.epsmart.recycling.device.base.BaseApplication;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 15:06
 * @description: （添加一句描述）
 */
public class NetworkUtils {

    /**
     * Return whether wifi is connected.
     * <p>Must hold
     * {@code <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />}</p>
     *
     * @return {@code true}: connected<br>{@code false}: disconnected
     */
    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isWifiConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) BaseApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        //noinspection ConstantConditions
        return cm.getActiveNetworkInfo() != null
                && cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

}
