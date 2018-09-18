package cn.epsmart.recycling.device.ui.fragment.home;

import android.os.Bundle;
import android.view.View;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:04
 * @description: （显示首页内容）
 */
public class HomeFragment extends BaseMvpFragment<HomePresenter> {
    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_home_main;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
