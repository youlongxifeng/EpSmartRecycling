package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.SettlementBean;
import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import io.reactivex.Observable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:18
 * @description: （物品结算界面）
 */
public interface ArticleSettlementContract {
    interface View extends BaseView {
        /**
         * 获取初始重量成功
         */
        void InitWeightSuccess(String  str);

        /**
         * 获取初始重量失败
         */
        void InitWeightFailed(String  str);

        /**
         * 获取交易物品重量成功
         */
        void ArticleWeightSuccess(SettlementBean settlementBean);

        /**
         * 获取交易物品重量失败
         */
        void ArticleWeightFailed(String  str);

        /**
         * 上报交易结果成功
         */
        void updateProceedsSuccess(String  str);

        /**
         * 上报交易结果失败
         */
        void updateProceedsFailed(String  str);

    }

    interface Model extends BaseModel {
        /**
         * 获取物品初始化重量参数
         * @return
         */
        Observable<ResponseBean<String>> getWeightParameter();

        /**
         * 获取此次物品重量参数
         * @return
         */
        Observable<ResponseBean<String>>  getArticleWeight();

        /**
         * 上报此次物品重量到服务器
         * @return
         */
        Observable<ResponseBean<String>>  updateProceeds();


    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 初始化当前回收桶重量获取配置参数
         */
        abstract void getInitWeightParameter(RecoveryTypeBean articleType);

        /**
         * 结算时获取计算重量
         */
        abstract void getCurrentArticleWeight(RecoveryTypeBean recoveryTypeBean);

        /**
         * 上报收益
         */
        abstract void updateProceeds();


    }
}
