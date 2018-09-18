package cn.epsmart.recycling.device.ui.fragment.articlesettlement;

import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 19:18
 * @description: （添加一句描述）
 */
public interface ArticleSettlementContract {
    interface View extends BaseView {

    }

    interface Model extends BaseModel {


    }

    abstract class Presenter extends BasePresenter<View, Model> {


    }
}
