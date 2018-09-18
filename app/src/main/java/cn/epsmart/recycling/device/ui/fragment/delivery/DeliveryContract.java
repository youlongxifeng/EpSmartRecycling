package cn.epsmart.recycling.device.ui.fragment.delivery;

import org.json.JSONObject;

import java.util.Map;

import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import cn.epsmart.recycling.device.ui.fragment.home.HomeContract;
import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:59
 * @description: （添加一句描述）
 */
public interface DeliveryContract {
    interface View extends BaseView {

    }

    interface Model extends BaseModel {


    }

    abstract class Presenter extends BasePresenter<View, Model> {


    }
}
