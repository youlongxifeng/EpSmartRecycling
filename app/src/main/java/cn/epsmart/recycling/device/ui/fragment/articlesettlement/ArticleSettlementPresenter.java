package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.entity.RecoveryProceedsBean;
import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import cn.epsmart.recycling.device.mvp.rx.ApiException;
import cn.epsmart.recycling.device.mvp.rx.HttpDisposableObserver;
import cn.epsmart.recycling.device.mvp.rx.RxSchedulers;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:19
 * @description: （添加一句描述）
 */
public class ArticleSettlementPresenter extends ArticleSettlementContract.Presenter {
    public ArticleSettlementPresenter() {
        mModel = new ArticleSettlementModel();
    }

    /**
     * 第一次获取历史设备中回收物的历史重量
     *
     * @param articleType
     */
    @Override
    void getHistoryWeightParameter(RecoveryTypeBean articleType) {
        HttpDisposableObserver<SettlementBean> httpDisposableObserver = new HttpDisposableObserver<SettlementBean>() {
            @Override
            public void onNext(SettlementBean stringResponseBean) {
                if (mView != null) {
                    mView.historyWeightSuccess(stringResponseBean);
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    mView.historyWeightFailed("获取数据失败" + e.getMsg());
                }
            }
        };
        mModel.getArticleWeight()
                .compose(RxSchedulers.<SettlementBean>switchObservableThread())
                .subscribe(httpDisposableObserver);
        addSubscribe(httpDisposableObserver);
    }

    /**
     * 第二次获取产品重量
     * @param recoveryTypeBean
     */
    @Override
    void getCurrentArticleWeight(RecoveryTypeBean recoveryTypeBean) {

        HttpDisposableObserver<SettlementBean> httpDisposableObserver = new HttpDisposableObserver<SettlementBean>() {
            @Override
            public void onNext(SettlementBean stringResponseBean) {
                if (mView != null) {
                    SettlementBean settlementBean = new SettlementBean();
                    settlementBean.setPrice(String.valueOf(recoveryTypeBean.getmRecoveryPrice()));
                    settlementBean.setWeight("26");
                    mView.presentArticleWeightSuccess(settlementBean);
                } else {
                    if(mView!=null)
                    mView.presentArticleWeightFailed("失败");
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    SettlementBean settlementBean = new SettlementBean();
                    settlementBean.setPrice(String.valueOf(recoveryTypeBean.getmRecoveryPrice()));
                    settlementBean.setWeight("26");
                    mView.presentArticleWeightSuccess(settlementBean);
                } else {
                    if(mView!=null)
                    mView.presentArticleWeightFailed("失败");
                }
            }
        };
        mModel.getArticleWeight()
                .compose(RxSchedulers.<SettlementBean>switchObservableThread())
                .subscribe(httpDisposableObserver);
        addSubscribe(httpDisposableObserver);


    }

    /**
     * 商报物品重量
     */
    @Override
    void updateWeight(String weight, String type) {
        HttpDisposableObserver<ResponseBean<RecoveryProceedsBean>> disposableObserver = getDisposableObserver();
        mModel.updateWeight(weight, type)
                .compose(RxSchedulers.<ResponseBean<RecoveryProceedsBean>>switchObservableThread())
                .subscribe(disposableObserver);
        addSubscribe(disposableObserver);


    }


    private HttpDisposableObserver<ResponseBean<RecoveryProceedsBean>> getDisposableObserver() {
        return new HttpDisposableObserver<ResponseBean<RecoveryProceedsBean>>() {
            @Override
            public void onNext(ResponseBean<RecoveryProceedsBean> recoveryTypeBeans) {
                if (mView != null) {
                    // RecoveryProceedsBean singleDetailsBean=recoveryTypeBeans.getData();
                    RecoveryProceedsBean proceedsBean = new RecoveryProceedsBean();
                    proceedsBean.setmProceeds("25");
                    proceedsBean.setmCompany("15");
                    mView.updateProceedsSuccess(proceedsBean);
                } else {
                    if(mView!=null)
                    mView.updateProceedsFailed("数据上报失败");
                }
            }

            @Override
            public void onError(ApiException e) {
                if (mView != null) {
                    RecoveryProceedsBean proceedsBean = new RecoveryProceedsBean();
                    proceedsBean.setmProceeds("25");
                    proceedsBean.setmCompany("元");
                    mView.updateProceedsSuccess(proceedsBean);
                } else {
                    if(mView!=null)
                    mView.updateProceedsFailed("数据上报失败");
                }
            }
        };
    }


}
