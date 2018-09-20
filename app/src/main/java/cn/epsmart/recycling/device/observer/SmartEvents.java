package cn.epsmart.recycling.device.observer;

import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.EventBus;

import cn.epsmart.recycling.device.MyEventBusIndex;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 13:49
 * @description: （添加一句描述）
 */
public class SmartEvents {
    private final static SmartEvents mInstance = new SmartEvents();
    public static void post(Object obj){
        mInstance.mEventsBus.post(obj);
    }

    public static void post(final Object obj,long delay)
    {
        if(delay > 0){
            mInstance.mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mInstance.mEventsBus.post(obj);
                }
            },delay);
        }else{
            mInstance.mEventsBus.post(obj);
        }
    }

    public static void register(Object obj)
    {
        mInstance.mEventsBus.register(obj);
    }

    public static void unRegister(Object obj){
        mInstance.mEventsBus.unregister(obj);
    }

    private final Handler mHandler;
    private final EventBus mEventsBus;
    private SmartEvents()
    {
        //添加索引,添加索引后只有执行相关索引MyEventBusIndex才会有，做记录
        mEventsBus = EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        mHandler = new Handler(Looper.getMainLooper());
    }
}
