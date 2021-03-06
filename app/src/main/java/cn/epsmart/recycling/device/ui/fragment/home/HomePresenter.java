package cn.epsmart.recycling.device.ui.fragment.home;

import com.company.project.android.utils.LogUtils;
import com.company.project.android.utils.NetWorkUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseApplication;
import cn.epsmart.recycling.device.constant.Constant;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.mvp.rx.ApiException;
import cn.epsmart.recycling.device.mvp.rx.HttpDisposableObserver;
import cn.epsmart.recycling.device.mvp.rx.RxSchedulers;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by xiexie on 2018/9/16.
 */

public class HomePresenter extends HomeContract.Presenter {
    private final static String TAG = HomePresenter.class.getSimpleName();
    private String[] mTypeName = {"纸类", "塑料", "金属", "纺织品", "玻璃", "有害"};
    private int[] mPrice = {15, 11, 12, 22, 0, 0};
    private int[] mIcon = {R.mipmap.paper, R.mipmap.plastic, R.mipmap.metal, R.mipmap.clothes, R.mipmap.glass, R.mipmap.harmful};
    private int curpage=1;

    public HomePresenter() {
        mModel = new HomeModel();
    }

    @Override
    void exitLogon() {
        //以后需要执行退出登录操作，数据清理操作
        if (mView != null) {
            mView.exitLogon();
        }
    }

    /**
     * 获取回收类型数据
     *
     * @param maps
     */
    @Override
    public void getDeliveryData(Map<String, String> maps) {
        DisposableObserver<List<RecoveryTypeBean>> disposableObserver = getDisposableObserver();
        mModel.getDeliveryData(Constant.PAGE_SIZE,curpage)
                .compose(RxSchedulers.<List<RecoveryTypeBean>>combine())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);
    }


    private HttpDisposableObserver<List<RecoveryTypeBean>> getDisposableObserver() {
        return new HttpDisposableObserver<List<RecoveryTypeBean>>() {
            @Override
            public void onNext(List<RecoveryTypeBean> recoveryTypeBeans) {
                LogUtils.i(TAG, "(mView != null)=onNext==recoveryTypeBeans=" + recoveryTypeBeans);
                if (mView != null) {
                    mView.setDeliveryDataSucceed(recoveryTypeBeans);
                }
            }

            @Override
            public void onError(ApiException e) {
                LogUtils.i(TAG, "(mView != null)onError=e===" + e);
                List<RecoveryTypeBean> mRecoveryTypeBeanList = new ArrayList<>();
                for (int i = 0; i < mTypeName.length; i++) {
                    RecoveryTypeBean recoveryTypeBean = new RecoveryTypeBean();
                    recoveryTypeBean.setId(i);
                    recoveryTypeBean.setPrice(mPrice[i]);
                    recoveryTypeBean.setCategoryId(String.valueOf(i));
                    recoveryTypeBean.setName(mTypeName[i]);
                    recoveryTypeBean.setImgure(mIcon[i]);
                    mRecoveryTypeBeanList.add(recoveryTypeBean);
                }
                if (NetWorkUtil.connection(BaseApplication.getContext())) {
                    if (mView != null) {
                        //mView.setDeliveryDataSucceed(mRecoveryTypeBeanList);
                        mView.setDeliveryDataFail(e.getMsg());
                    }
                } else {                }

            }
        };
    }

}
