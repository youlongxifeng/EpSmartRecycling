package cn.epsmart.recycling.device.mvp.rx;

import io.reactivex.observers.DisposableObserver;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/26 9:41
 * @description: （重写观察者）
 */
public  abstract class HttpDisposableObserver<T>  extends DisposableObserver<T>  {
    @Override
    protected void onStart() {
        super.onStart();
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
