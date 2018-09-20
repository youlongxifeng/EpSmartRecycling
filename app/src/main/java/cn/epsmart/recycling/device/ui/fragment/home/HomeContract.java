package cn.epsmart.recycling.device.ui.fragment.home;

import org.json.JSONObject;

import java.util.List;
import java.util.Map;

import cn.epsmart.recycling.device.entity.RecoveryTypeBean;
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
         * @param active
         */
        void setDeliveryDataSucceed(List<RecoveryTypeBean> active);

        /**
         * 获取物品类型失败
         * @param fail
         */
        void setDeliveryDataFail(String fail);

        /**
         * 获取物品收益列表列表成功
         * @param active
         */
        void setTotalRriceDataSucceed(List<RecoveryTypeBean> active);

        /**
         * 获取物品收益列表失败
         * @param fail
         */
        void setTotalRriceDataFail(String fail);

    }

    interface Model extends BaseModel {


        Observable<List<RecoveryTypeBean>> getDeliveryData();

    }

    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 退出登录
         */
        abstract void exitLogon();

        public abstract void getDeliveryData(Map<String, String> maps);

        /**
         * 收益明细
         * @param maps
         */
        public abstract void getObviousIncomeData(Map<String, String> maps);


    }
}
