package cn.epsmart.recycling.device.ui.activity.main;

import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 9:13
 * @description: （添加一句描述）
 */
public interface MainContract {
    interface View extends BaseView {}
    interface Model extends BaseModel {}
    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 更新位置信息
         */
        abstract void updatePosition();
    }
}
