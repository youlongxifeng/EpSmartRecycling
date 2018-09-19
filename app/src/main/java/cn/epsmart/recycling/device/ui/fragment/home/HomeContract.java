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

        void setDeliveryDataSucceed(List<RecoveryTypeBean> active);

    }

    interface Model extends BaseModel {


        Observable<List<RecoveryTypeBean>> getDeliveryData();

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract void getDeliveryData(Map<String, String> maps);


    }
}
