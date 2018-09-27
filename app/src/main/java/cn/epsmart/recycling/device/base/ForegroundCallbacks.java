package cn.epsmart.recycling.device.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.company.project.android.utils.LogUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在Application类中监听App处于前台和后台
 */

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 11:30
 * @description: （添加一句描述）
 */
public class ForegroundCallbacks implements Application.ActivityLifecycleCallbacks {
    private final static String TAG = ForegroundCallbacks.class.getSimpleName();
    public static final long CHECK_DELAY = 500;

    public interface Listener {
        void onBecameForeground();

        void onBecameBackground();
    }

    private static ForegroundCallbacks instance;

    private boolean foreground = false, paused = true;

    private Handler handler = new Handler();

    private List<Listener> listeners = new CopyOnWriteArrayList<>();

    private Runnable check;

    public static ForegroundCallbacks init(Application application) {
        if (instance == null) {
            instance = new ForegroundCallbacks();
            application.registerActivityLifecycleCallbacks(instance);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Application application) {
        if (instance == null) {
            init(application);
        }
        return instance;
    }

    public static ForegroundCallbacks get(Context ctx) {
        if (instance == null) {
            Context appCtx = ctx.getApplicationContext();
            if (appCtx instanceof Application) {
                init((Application) appCtx);
            }
            throw new IllegalStateException(
                    "Foreground is not initialised and " +
                            "cannot obtain the Application object");
        }
        return instance;
    }

    public static ForegroundCallbacks get() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Foreground is not initialised - invoke " +
                            "at least once with parameterised init/get");
        }
        return instance;
    }

    public boolean isForeground() {
        return foreground;
    }

    public boolean isBackground() {
        return !foreground;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        paused = false;
        boolean wasBackground = !foreground;
        foreground = true;
        if (check != null)
            handler.removeCallbacks(check);
        if (wasBackground) {
            LogUtils.e(TAG, "went foreground");
            for (Listener l : listeners) {
                try {
                    l.onBecameForeground();
                } catch (Exception exc) {
                    LogUtils.e("yumf", "Listener threw exception!");
                }
            }
        } else {
            LogUtils.e(TAG, "still foreground");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        paused = true;
        if (check != null)
            handler.removeCallbacks(check);
        handler.postDelayed(check = new Runnable() {
            @Override
            public void run() {
                if (foreground && paused) {
                    foreground = false;
                    LogUtils.e(TAG, "went background");
                    for (Listener l : listeners) {
                        try {
                            l.onBecameBackground();
                        } catch (Exception exc) {
                            LogUtils.e(TAG, "Listener threw exception!"+exc);
                        }
                    }
                } else {
                    LogUtils.e(TAG, "still foreground");
                }
            }
        }, CHECK_DELAY);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        LogUtils.i(TAG,"======onActivityCreated==========");

    }

    @Override
    public void onActivityStarted(Activity activity) {
        LogUtils.i(TAG,"======onActivityStarted==========");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        LogUtils.i(TAG,"======onActivityStopped=========="+activity.getClass().getName());
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        LogUtils.i(TAG,"======onActivitySaveInstanceState==========");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        LogUtils.i(TAG,"======onActivityDestroyed==========");
        if(handler!=null){
            handler.removeCallbacksAndMessages(null);
        }
    }
}
