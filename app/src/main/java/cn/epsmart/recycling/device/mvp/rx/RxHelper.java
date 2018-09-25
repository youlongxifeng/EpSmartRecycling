package cn.epsmart.recycling.device.mvp.rx;

import org.reactivestreams.Publisher;

import cn.epsmart.recycling.device.entity.ResponseBean;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 14:35
 * @description: （添加一句描述）
 */
public class RxHelper {
    /**
     * 统一线程处理
     * <p>
     * 发布事件io线程，接收事件主线程
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {//compose处理线程
        return new ObservableTransformer<T, T>() {

            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }




    /**
     * 生成Flowable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Flowable<T> createFlowable(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * 生成Observable
     *
     * @param t
     * @return Flowable
     */
    public static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        });
    }


    public static <T> FlowableTransformer<ResponseBean<T>, T> combineFlowable() {
        return new FlowableTransformer<ResponseBean<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<ResponseBean<T>> upstream) {
                return  upstream
                        .unsubscribeOn(Schedulers.io())
                        // 将被观察者切换到子线程
                        .subscribeOn(Schedulers.io())
                        // 将观察者切换到主线程
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new MapFunction())
                        .onErrorResumeNext(new ErrorObservableSource());
            }


        };
    }


    /**
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<ResponseBean<T>, T> combineObservable() {
        return new ObservableTransformer<ResponseBean<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<ResponseBean<T>> upstream) {
                return  upstream
                        .unsubscribeOn(Schedulers.io())
                        // 将被观察者切换到子线程
                        .subscribeOn(Schedulers.io())
                        // 将观察者切换到主线程
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new MapFunction())
                        .onErrorResumeNext(new ErrorObservableSource());
            }
        };
    }
}
