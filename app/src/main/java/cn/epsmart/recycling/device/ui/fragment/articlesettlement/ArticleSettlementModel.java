package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.api.ApiEngine;
import cn.epsmart.recycling.device.entity.RecoveryProceedsBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:22
 * @description: （添加一句描述）
 */
public class ArticleSettlementModel implements ArticleSettlementContract.Model {

    /**
     * 第一次获取产品历史重量
     * @return
     */
    @Override
    public Observable<ResponseBean<String>> getHistoryWeightParameter() {
        //和串口通讯，获取当前设备配置信息
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                SettlementBean settlementBean=new SettlementBean();
                settlementBean.setWeight("15");
                settlementBean.setPrice("25");
                emitter.onNext(settlementBean);
                emitter.onComplete();
            }
        });

        return observable;
    }

    /**
     * 第二次获取获取当前产品的历史重量
     * @return
     */
    @Override
    public Observable<SettlementBean> getArticleWeight() {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                SettlementBean settlementBean=new SettlementBean();
                settlementBean.setWeight("15");
                settlementBean.setPrice("0");
                emitter.onNext(settlementBean);
                emitter.onComplete();
            }
        });

        return observable;
    }



    /**
     * 上报回收物品重量
     *
     * @param weight
     * @param type
     * @return
     */
    @Override
    public Observable<ResponseBean<RecoveryProceedsBean>> updateWeight(String weight,String oldweight, String type) {
        return ApiEngine.getInstance().getApiService().updateWeight(weight,oldweight, type);
    }
}
