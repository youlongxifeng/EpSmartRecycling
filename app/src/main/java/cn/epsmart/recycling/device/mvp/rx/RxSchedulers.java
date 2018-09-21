package cn.epsmart.recycling.device.mvp.rx;

import org.reactivestreams.Publisher;

import cn.epsmart.recycling.device.entity.ResponseBean;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:19
 * @description: （添加一句描述）
 */
public class RxSchedulers {
    public static <T> FlowableTransformer<T, T> switchFlowableThread() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理 ompose简化线程
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> switchObservableThread() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream
                        .subscribeOn(Schedulers.io())//指定的就是发射事件的线程
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());//指定的就是订阅者接收事件的线程。
            }
        };
    }


    public static <T> ObservableTransformer<ResponseBean<T>, T> combine() {
        return new ObservableTransformer<ResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBean<T>> upstream) {
                return  upstream
                        .subscribeOn(Schedulers.io())
                        .unsubscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new MapFunction())
                        .onErrorResumeNext(new ErrorObservableSource());
            }
        };
    }



}
