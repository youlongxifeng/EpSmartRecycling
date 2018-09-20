package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.entity.ResponseBean;
import io.reactivex.Observable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 15:22
 * @description: （添加一句描述）
 */
public class ArticleSettlementModel implements ArticleSettlementContract.Model {


    @Override
    public Observable<ResponseBean<String>> getWeightParameter() {
        //和串口通讯，获取当前设备配置信息
        return null;
    }

    @Override
    public Observable<ResponseBean<String>> getArticleWeight() {
        return null;
    }

    @Override
    public Observable<ResponseBean<String>> updateProceeds() {
        return null;
    }
}
