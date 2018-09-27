package cn.epsmart.recycling.device.utils;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;

import java.util.List;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 11:21
 * @description: （添加一句描述）
 */
public class CommonUtil {

    /**
     * APP尺寸
     * @param context
     * @param dimenId
     * @return
     */
    public static int getDimens(Context context, int dimenId) {
        return context.getResources().getDimensionPixelOffset(dimenId);
    }

    /**
     * 判断当前是否是前台线程
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppIsInBackground(Context context,String  packageName) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                //前台程序
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (packageName.equals(activeProcess)) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (packageName.equals(componentInfo.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

}
