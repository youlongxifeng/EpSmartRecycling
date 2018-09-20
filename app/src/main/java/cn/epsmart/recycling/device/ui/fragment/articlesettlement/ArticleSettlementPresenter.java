package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.SettlementBean;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:19
 * @description: （添加一句描述）
 */
public class ArticleSettlementPresenter extends ArticleSettlementContract.Presenter {
    public ArticleSettlementPresenter() {
        mModel = new ArticleSettlementModel();
    }


    @Override
    void getInitWeightParameter(RecoveryTypeBean articleType) {
        if (mView != null) {
            mView.InitWeightSuccess("数据获取成功");
        } else {
            mView.InitWeightFailed("获取数据失败");
        }

    }

    @Override
    void getCurrentArticleWeight(RecoveryTypeBean recoveryTypeBean) {
        if (mView != null) {
            SettlementBean settlementBean=new SettlementBean();
            settlementBean.setPrice(recoveryTypeBean.getmRecoveryPrice());
            settlementBean.setWeight("26");
            mView.ArticleWeightSuccess(settlementBean);
        } else {
            mView.ArticleWeightFailed("失败");
        }

    }

    @Override
    void updateProceeds() {
        if (mView != null) {
            mView.updateProceedsSuccess("数据上报成功");
        } else {
            mView.updateProceedsFailed("数据上报失败");
        }
    }
}
