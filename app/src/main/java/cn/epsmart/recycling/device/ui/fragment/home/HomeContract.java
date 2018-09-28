package cn.epsmart.recycling.device.ui.fragment.home;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;
import okhttp3.RequestBody;

/**
 * Created by xiexie on 2018/9/16.
 */

public interface HomeContract {
    interface View extends BaseView {
        /**
         * 退出登录
         */
        void exitLogon();

        /**
         * 获取物品类型列表成功
         *
         * @param active
         */
        void setDeliveryDataSucceed(List<RecoveryTypeBean> active);

        /**
         * 获取物品类型失败
         *
         * @param fail
         */
        void setDeliveryDataFail(String fail);
        /**
        * 没有网络
        */
        // void noNetwork(String noNetwork);


    }

    interface Model extends BaseModel {


        Observable<ResponseBean<List<RecoveryTypeBean>>> getDeliveryData(int pageSize, int curPage);

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 退出登录
         */
        abstract void exitLogon();

        /**
         * 获取回收类型数据
         *
         * @param maps
         */
        public abstract void getDeliveryData(Map<String, String> maps);


    }
}
