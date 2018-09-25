package cn.epsmart.recycling.device.ui.activity.signin;

import cn.epsmart.recycling.device.entity.UserBean;
import cn.epsmart.recycling.device.mvp.rx.RxHelper;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 13:18
 * @description: （添加一句描述）
 */
public class SignInPresenter extends SignInContract.Presenter {
    public SignInPresenter() {
        mModel = new SignInModel();
    }

    @Override
    void signIn(String username, String password) {
        DisposableSubscriber<UserBean> disposableSubscriber =
                new DisposableSubscriber<UserBean>() {
                    @Override
                    public void onNext(UserBean userBean) {
                        if (mView != null) {
                           // UserBean userBean = responseBean.getData();

                            mView.signInSuccess(userBean);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView != null) {
                            mView.signInFail();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                };
        mModel.userLogin(username, password)
                .compose(RxHelper.<UserBean>combineFlowable())
                .subscribe(disposableSubscriber);
        addSubscribe(disposableSubscriber);

    }
}
