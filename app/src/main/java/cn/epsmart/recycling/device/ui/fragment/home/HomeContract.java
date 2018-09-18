package cn.epsmart.recycling.device.ui.fragment.home;

import org.json.JSONObject;

import java.util.Map;

import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import io.reactivex.Observable;
import okhttp3.RequestBody;

/**
 * Created by xiexie on 2018/9/16.
 */

public interface HomeContract {
    interface View extends BaseView {

        void setLogin(String active);

        void showDialog();

        void onSucceed(String data);

        void onFail(String err);

        void hideDialog();
    }

    interface Model extends BaseModel {

        String loginSuccess();//Success, failure
        Observable<String> getGank();

        Observable<JSONObject> accessToken(RequestBody requestBody);

    }

    abstract class Presenter extends BasePresenter<View, Model> {

        public abstract String login(Map<String, String> maps);

        public abstract void getGank();

        public abstract void  accessToken(String app_id,String secret_key);

    }
}
