package cn.epsmart.recycling.device.ui.fragment.home;

import com.company.project.android.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.mvp.rx.RxSchedulers;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by xiexie on 2018/9/16.
 */

public class HomePresenter extends HomeContract.Presenter {
    private final static String TAG=HomePresenter.class.getSimpleName();
    private String[]mTypeName={"纸类","塑料","金属","纺织品","玻璃","有害"};
    public HomePresenter() {
        mModel = new HomeModel();
    }

    @Override
    public void getDeliveryData(Map<String, String> maps) {
        List<RecoveryTypeBean> mRecoveryTypeBeanList = new ArrayList<>();
        for (int i = 0; i < mTypeName.length; i++) {
            RecoveryTypeBean recoveryTypeBean = new RecoveryTypeBean();
            recoveryTypeBean.setId(i);
            recoveryTypeBean.setmRecoveryPrice("10元/斤");
            recoveryTypeBean.setmRecoveryType(mTypeName[i]);
            mRecoveryTypeBeanList.add(recoveryTypeBean);
        }

        LogUtils.i(TAG,"(mView != null)="+(mView != null)+"   mRecoveryTypeBeanList="+mRecoveryTypeBeanList);
        if (mView != null) {
            mView.setDeliveryDataSucceed(mRecoveryTypeBeanList);
        }
       /* DisposableObserver<List<RecoveryTypeBean>> disposableObserver = getDisposableObserver();
        mModel.getDeliveryData()
                .compose(RxSchedulers.<List<RecoveryTypeBean>>switchObservableThread())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);*/

    }

    private DisposableObserver<List<RecoveryTypeBean>> getDisposableObserver() {
        return new DisposableObserver<List<RecoveryTypeBean>>() {
            @Override
            public void onNext(List<RecoveryTypeBean> recoveryTypeBeans) {
                if (mView != null) {
                    mView.setDeliveryDataSucceed(recoveryTypeBeans);
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }

}
