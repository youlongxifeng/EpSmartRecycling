package cn.epsmart.recycling.device.ui.activity.signin;

import java.util.List;

import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.mvp.BaseModel;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.mvp.BaseView;
import cn.epsmart.recycling.device.ui.activity.main.MainContract;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 13:17
 * @description: （添加一句描述）
 */
public interface SignInContract {
    interface View extends BaseView {
        /**
         * 登录成功
         */
        void signInSuccess(  UserBean userBean );

        /**
         * 登录失败
         */
        void signInFail();
    }
    interface Model extends BaseModel {
        Flowable<ResponseBean<UserBean>> userLogin(String phoneNumber, String password);
    }
    abstract class Presenter extends BasePresenter<View, Model> {
        /**
         * 更新位置信息
         */
        abstract void signIn(String name,String password);
    }
}
