package cn.epsmart.recycling.device.mvp.rx;

import org.reactivestreams.Subscription;

import io.reactivex.FlowableSubscriber;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/27 12:41
 * @description: （添加一句描述）
 */
public  abstract class HttpFlowableSubscriber<T> implements FlowableSubscriber<T> {
    @Override
    public void onSubscribe(Subscription s) {

    }

    @Override
    public void onNext(T t) {

    }


    @Override
    public void onError(Throwable e) {
        if (e instanceof ApiException) {
            onError((ApiException) e);
        } else {
            onError(new ApiException(e));
        }
    }

    @Override
    public void onComplete() {
    }

    public abstract void onError(ApiException e);
}
