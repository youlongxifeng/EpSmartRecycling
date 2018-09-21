package cn.epsmart.recycling.device.ui.activity.signin;

import cn.epsmart.recycling.device.api.ApiEngine;
import cn.epsmart.recycling.device.entity.ResponseBean;
import cn.epsmart.recycling.device.entity.UserBean;
import io.reactivex.Flowable;
import io.reactivex.Observable;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/21 13:22
 * @description: （添加一句描述）
 */
public class SignInModel implements SignInContract.Model {
    @Override
    public Flowable<ResponseBean<UserBean>> userLogin(String phoneNumber, String password) {
        return ApiEngine.getInstance().getApiService().userLogin(phoneNumber,password);
    }
}
