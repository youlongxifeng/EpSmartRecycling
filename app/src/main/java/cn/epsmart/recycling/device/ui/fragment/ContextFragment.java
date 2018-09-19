package cn.epsmart.recycling.device.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.company.project.android.utils.LogUtils;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseMvpFragment;
import cn.epsmart.recycling.device.mvp.BasePresenter;
import cn.epsmart.recycling.device.ui.fragment.home.HomeFragment;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 13:41
 * @description: （添加一句描述）
 */
public class ContextFragment extends BaseMvpFragment {
    private final static String TAG = ContextFragment.class.getSimpleName();
    public static ContextFragment newInstance() {

        Bundle args = new Bundle();

        ContextFragment fragment = new ContextFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getlayoutId() {
        return R.layout.fragment_context_main;
    }

    @Override
    protected void initView(View view) {

    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadMultipleRootFragment(R.id.fl_container, 0, HomeFragment.newInstance());
    }
    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        LogUtils.i(TAG,"requestCode=="+requestCode+"  resultCode="+resultCode);
    }

    @Override
    protected void initDate() {

    }

    /**
     * start other BrotherFragment
     */
    public void startBrotherFragment(SupportFragment targetFragment) {
        start(targetFragment);
    }
}
