package cn.epsmart.recycling.device.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import cn.epsmart.recycling.device.ui.activity.main.MainActivity;
import cn.epsmart.recycling.device.utils.CommonUtil;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 19:21
 * @description: （添加一句描述）
 */
public class GuardService extends Service {
    private final static String TAG=GuardService.class.getSimpleName();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG,"onCreate=========");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"onStartCommand=========");
        //延时3000 ，每间隔3000，时间单位
        Observable.interval(3000, 3000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                boolean IsInBackground = CommonUtil.isAppIsInBackground(GuardService.this, "cn.epsmart.recycling.device");
                Log.i(TAG,"================IsInBackground="+IsInBackground);
                if (IsInBackground) {
                    ComponentName componentName = new ComponentName("cn.epsmart.recycling.device", "cn.epsmart.recycling.device.ui.activity.main.MainActivity");
                    Intent intent = new Intent();
                    intent.setComponent(componentName);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//(Intent.FLAG_ACTIVITY_NEW_TASK);//这里需要修改的当前还不知道什么意思
                    startActivity(intent);
   }
            }
        });
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
