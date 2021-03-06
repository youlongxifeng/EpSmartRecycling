package cn.epsmart.recycling.device.ui.fragment.delivery;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/18 18:57
 * @description: （投递展示页面）
 */
public class DeliveryFragment extends BaseMvpFragment<DeliveryPresenter> {
    @BindView(R.id.recyclerview_delivery)
    RecyclerView mRecyclerView;



    public static DeliveryFragment newInstance( ) {

        Bundle args = new Bundle();
        DeliveryFragment fragment = new DeliveryFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected DeliveryPresenter createPresenter() {
        return new DeliveryPresenter();
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_delivery_main;
    }

     @Override
    protected View getRootView(ViewGroup container) {
        return mInflater.inflate( R.layout.fragment_delivery_main,container,false);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initDate() {

    }
}
