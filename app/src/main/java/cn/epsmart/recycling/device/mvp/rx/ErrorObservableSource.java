package cn.epsmart.recycling.device.mvp.rx;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.functions.Function;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 15:08
 * @description: （添加一句描述）
 */
public class ErrorObservableSource implements Function<Throwable, ObservableSource> {
    @Override
    public ObservableSource apply(Throwable throwable) throws Exception {
        return Observable.error(ApiException.handleException(throwable));
    }
}
