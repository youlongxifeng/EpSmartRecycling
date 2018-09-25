package cn.epsmart.recycling.device.ui.fragment.signin;

import android.view.View;
import android.view.ViewGroup;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.mvp.BasePresenter;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/20 16:48
 * @description: （登录页面）
 */
public class SignInFragment extends BaseMvpFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

     @Override
    protected int getlayoutId() {
        return R.layout.activity_sigin_main;
    }

    @Override
    protected View getRootView(ViewGroup container) {
        return mInflater.inflate( R.layout.activity_sigin_main,container,false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
