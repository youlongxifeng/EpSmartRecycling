package cn.epsmart.recycling.device.ui.activity.main;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.company.project.android.utils.LogUtils;

import org.reactivestreams.Subscription;

import java.util.List;

import cn.epsmart.recycling.device.manage.MapManage;
import cn.epsmart.recycling.device.mvp.rx.RxSchedulers;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 9:15
 * @description: （添加一句描述）
 */
public class MainPresenter extends MainContract.Presenter {
    private final static String TAG=MainPresenter.class.getSimpleName();
    public MainPresenter() {

    }

    @Override
    void updatePosition() {
        MapManage mapManage = new MapManage();
        Disposable subscribe = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                mapManage.startLocation();
                LogUtils.i(TAG,"====updatePosition=subscribe====");
            }
        })
                // 将被观察者切换到子线程
                .subscribeOn(Schedulers.io())
                // 将观察者切换到主线程
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        LogUtils.i(TAG,"====updatePosition=accept====");
                        mapManage.stopLocation();
                        mapManage.destroyLocation();
                    }
                });
        addSubscribe(subscribe);
    }
}
